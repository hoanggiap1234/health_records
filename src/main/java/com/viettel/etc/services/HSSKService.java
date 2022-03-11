package com.viettel.etc.services;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import com.squareup.okhttp.Response;
import com.viettel.etc.dto.PatientDTO;
import com.viettel.etc.dto.RequestSyncHsskDTO;
import com.viettel.etc.repositories.tables.*;
import com.viettel.etc.repositories.tables.entities.*;
import com.viettel.etc.utils.Constants;
import com.viettel.etc.utils.*;
import org.apache.log4j.Logger;
import org.hl7.fhir.r4.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HSSKService {
    private static final Logger LOGGER = Logger.getLogger(HSSKService.class);

    @Autowired
    private MedicalHealthcarePrehistoricRepositoryJPA medicalHealthcarePrehistoricRepositoryJPA;

    @Autowired
    private CatsRelationshipsRepositoryJPA catsRelationshipsRepositoryJPA;

    @Autowired
    private MedicalHealthcareAllergyRepositoryJPA medicalHealthcareAllergyRepositoryJPA;

    @Autowired
    private MedicalHealthcarePresurgeryRepositoryJPA medicalHealthcarePresurgeryRepositoryJPA;

    @Autowired
    private MedicalHealthcareVaccinationsRepositoryJPA medicalHealthcareVaccinationsRepositoryJPA;

    @Autowired
    private MedicalHealthcareHistoriesRepositoryJPA medicalHealthcareHistoriesRepositoryJPA;

    @Autowired
    private MedicalHealthcareHistoriesIcdRepositoryJPA medicalHealthcareHistoriesIcdRepositoryJPA;

    @Autowired
    private MedicalHealthcareDrugsRepositoryJPA medicalHealthcareDrugsRepositoryJPA;

    @Autowired
    private MedicalHealthcareServicesRepositoryJPA medicalHealthcareServicesRepositoryJPA;

    @Autowired
    private MedicalHealthcareServicesResultsRepositoryJPA medicalHealthcareServicesResultsRepositoryJPA;

    @Autowired
    private MedicalHealthcarePatientDetailRepositoryJPA medicalHealthcarePatientDetailRepositoryJPA;

    @Autowired
    private MessageService messageService;

    @Autowired
    private PatientsRepositoryJPA patientsRepositoryJPA;

    @Autowired
    private CatsHealthfacilitiesRepositoryJPA catsHealthfacilitiesRepositoryJPA;

    @Autowired
    private CatsMethodsRepositoryJPA catsMethodsRepositoryJPA;

    public void getDiseaseHistoryInfo(RequestSyncHsskDTO dto) throws Exception {
        String PID = dto.getPID();
        Integer patientId = dto.getPatientId();
        String token = dto.getToken();
        String url = System.getenv("HSSK_LB_URL") + "/hssk/get-disease-history-info/" + PID+
                "?from_date=" + dto.getFromDate() +
                "&to_date=" + dto.getToDate();
        String responseBody = requestDataHSSK(url, token);
        Bundle bundle = parseHL7ToObject(responseBody);
        List<MedicalHealthcarePrehistoricEntity> hsskEntities = new ArrayList<>();
        for (Bundle.BundleEntryComponent entry : bundle.getEntry()) {
            if (entry.getResource().getResourceType().compareTo(ResourceType.Condition) == 0) {
                Condition condition = (Condition) entry.getResource();
                MedicalHealthcarePrehistoricEntity entity = convertHapiObjToTelecareObj(patientId, condition);
                entity.setPhoneNumber(dto.getPhoneNumber());
                hsskEntities.add(entity);
            }
        }

        List<MedicalHealthcarePrehistoricEntity> oldEntities = medicalHealthcarePrehistoricRepositoryJPA.findByPatientIdAndRelationshipIdAndIsActiveAndIsDelete(patientId,Constants.RELATIONSHIP_ID_HIMSELF, Constants.IS_ACTIVE, Constants.IS_NOT_DELETE);
        HashSet<MedicalHealthcarePrehistoricEntity> hsetHsskEntities = new HashSet<>(hsskEntities);
        FnCommon.classifyCreateOrUpdateOrDelete(hsetHsskEntities, oldEntities);
        oldEntities.addAll(hsetHsskEntities);
        medicalHealthcarePrehistoricRepositoryJPA.saveAll(oldEntities);
    }

    @Transactional(rollbackFor = Exception.class)
    public void getDiseaseHistoryFamilyInfo(RequestSyncHsskDTO dto) throws Exception {
        String PID = dto.getPID();
        Integer patientId = dto.getPatientId();
        String token = dto.getToken();
        String url = System.getenv("HSSK_LB_URL") + "/hssk/get-disease-history-family-info/" + PID+
                "?from_date=" + dto.getFromDate() +
                "&to_date=" + dto.getToDate();
        String responseBody = requestDataHSSK(url, token);
        Bundle bundle = parseHL7ToObject(responseBody);
        List<MedicalHealthcarePrehistoricEntity> hsskEntities = new ArrayList<>();
        for (Bundle.BundleEntryComponent entry : bundle.getEntry()) {
            if (entry.getResource().getResourceType().compareTo(ResourceType.FamilyMemberHistory) == 0) {
                FamilyMemberHistory familyMemberHistory = (FamilyMemberHistory) entry.getResource();
                MedicalHealthcarePrehistoricEntity entity = convertHapiObjToTelecareObj(patientId, familyMemberHistory);
                entity.setPhoneNumber(dto.getPhoneNumber());
                hsskEntities.add(entity);
            }
        }

        List<MedicalHealthcarePrehistoricEntity> oldEntities = medicalHealthcarePrehistoricRepositoryJPA.findByPatientIdAndRelationshipIdIsNotAndIsActiveAndIsDelete(patientId, Constants.RELATIONSHIP_ID_HIMSELF, Constants.IS_ACTIVE, Constants.IS_NOT_DELETE);
        HashSet<MedicalHealthcarePrehistoricEntity> hsetHsskEntities = new HashSet<>(hsskEntities);
        FnCommon.classifyCreateOrUpdateOrDelete(hsetHsskEntities, oldEntities);
        oldEntities.addAll(hsetHsskEntities);
        medicalHealthcarePrehistoricRepositoryJPA.saveAll(oldEntities);
    }

    @Transactional(rollbackFor = Exception.class)
    public void getMedicalAllergyInfo(RequestSyncHsskDTO dto) throws Exception {
        String PID = dto.getPID();
        Integer patientId = dto.getPatientId();
        String token = dto.getToken();
        String url = System.getenv("HSSK_LB_URL") + "/hssk/get-medical-allergy-info/" + PID + "?from_date=01/01/2020&to_date=19/04/2021"+
                "?from_date=" + dto.getFromDate() +
                "&to_date=" + dto.getToDate();
        String responseBody = requestDataHSSK(url, token);
        Bundle bundle = parseHL7ToObject(responseBody);
        List<MedicalHealthcareAllergyEntity> hsskEntities = new ArrayList<>();
        for (Bundle.BundleEntryComponent entry : bundle.getEntry()) {
            if (entry.getResource().getResourceType().compareTo(ResourceType.AllergyIntolerance) == 0) {
                AllergyIntolerance allergyIntolerance = (AllergyIntolerance) entry.getResource();
                MedicalHealthcareAllergyEntity entity = convertHapiObjToTelecareObj(patientId, allergyIntolerance);
                entity.setPhoneNumber(dto.getPhoneNumber());
                hsskEntities.add(entity);
            }
        }

        List<MedicalHealthcareAllergyEntity> oldEntities = medicalHealthcareAllergyRepositoryJPA.findByPatientIdAndRelationshipIdAndIsActiveAndIsDelete(patientId, Constants.RELATIONSHIP_ID_HIMSELF, Constants.IS_ACTIVE, Constants.IS_NOT_DELETE);
        HashSet<MedicalHealthcareAllergyEntity> hsetHsskEntities = new HashSet<>(hsskEntities);
        FnCommon.classifyCreateOrUpdateOrDelete(hsetHsskEntities, oldEntities);
        oldEntities.addAll(hsetHsskEntities);
        medicalHealthcareAllergyRepositoryJPA.saveAll(oldEntities);
    }

    @Transactional(rollbackFor = Exception.class)
    public void getPresurgeryInfo(RequestSyncHsskDTO dto) throws Exception {
        String PID = dto.getPID();
        Integer patientId = dto.getPatientId();
        String token = dto.getToken();
        String url = System.getenv("HSSK_LB_URL") + "/hssk/get-presurgery-info/" + PID +
                "?from_date=" + dto.getFromDate() +
                "&to_date=" + dto.getToDate();
        String responseBody = requestDataHSSK(url, token);
        Bundle bundle = parseHL7ToObject(responseBody);
        HashMap<String, Procedure> hmapProcedure = new HashMap<>();
        HashMap<String, String> hmapOrganizationCode = new HashMap<>();
        for (Bundle.BundleEntryComponent entry : bundle.getEntry()) {
            if (entry.getResource().getResourceType().compareTo(ResourceType.Procedure) == 0) {
                Procedure procedure = (Procedure) entry.getResource();
                String healthfacilitiesUUID = procedure.getPerformerFirstRep().getOnBehalfOf().getReference().replace("Organization/", "");
                hmapProcedure.put(healthfacilitiesUUID, procedure);
            } else if (entry.getResource().getResourceType().compareTo(ResourceType.Organization) == 0) {
                Organization organization = (Organization) entry.getResource();
                String healthfacilitiesUUID = organization.getId().replace("urn:uuid:", "");
                String healthfacilitiesCode = organization.getName();
                hmapOrganizationCode.put(healthfacilitiesUUID, healthfacilitiesCode);
            }
        }

        List<MedicalHealthcarePresurgeryEntity> hsskEntities = new ArrayList<>();
        hmapProcedure.forEach((healthfacilitiesUUID, procedure) -> {
            MedicalHealthcarePresurgeryEntity entity = convertHapiObjToTelecareObj(procedure, hmapOrganizationCode, patientId, healthfacilitiesUUID);
            entity.setPhoneNumber(dto.getPhoneNumber());
            hsskEntities.add(entity);
        });

        List<MedicalHealthcarePresurgeryEntity> oldEntities = medicalHealthcarePresurgeryRepositoryJPA.findByPatientIdAndRelationshipIdAndIsActiveAndIsDelete(patientId, Constants.RELATIONSHIP_ID_HIMSELF, Constants.IS_ACTIVE, Constants.IS_NOT_DELETE);
        HashSet<MedicalHealthcarePresurgeryEntity> hsetHsskEntities = new HashSet<>(hsskEntities);
        FnCommon.classifyCreateOrUpdateOrDelete(hsetHsskEntities, oldEntities);
        oldEntities.addAll(hsetHsskEntities);
        medicalHealthcarePresurgeryRepositoryJPA.saveAll(oldEntities);
    }

    @Transactional(rollbackFor = Exception.class)
    public void getImmunizationInfo(RequestSyncHsskDTO dto, PatientDTO patientInfo) throws Exception {
        String PID = dto.getPID();
        Integer patientId = dto.getPatientId();
        String token = dto.getToken();
        String url = System.getenv("HSSK_LB_URL") + "/hssk/get-immunization-info/" + PID +
                "?from_date=" + dto.getFromDate() +
                "&to_date=" + dto.getToDate();;

        String responseBody = requestDataHSSK(url, token);
        Bundle bundle = parseHL7ToObject(responseBody);
        List<MedicalHealthcareVaccinationsEntity> hsskEntities = new ArrayList<>();

        HashMap<String, Immunization> hmapImmunization = new HashMap<>();
        HashMap<String, Observation> hmapReactionAfterInjection = new HashMap<>();
        HashMap<String, Organization> hmapOrganization = new HashMap<>();

        for (Bundle.BundleEntryComponent entry : bundle.getEntry()) {
            if (entry.getResource().getResourceType().compareTo(ResourceType.Immunization) == 0) {
                Immunization immunization = (Immunization) entry.getResource();
                String reactionAfterInjectionId = immunization.getReactionFirstRep().getDetail().getReference().replace("Observation/", "");
                hmapImmunization.put(reactionAfterInjectionId, immunization);
            } else if (entry.getResource().getResourceType().compareTo(ResourceType.Observation) == 0) {
                Observation observation = (Observation) entry.getResource();
                String reactionAfterInjectionId = observation.getId().replace("urn:uuid:", "");
                hmapReactionAfterInjection.put(reactionAfterInjectionId, observation);
            } else if (entry.getResource().getResourceType().compareTo(ResourceType.Organization) == 0) {
                this.ToHmapOrgnization(hmapOrganization, entry);
            }
        }

        hmapImmunization.forEach((reactionAfterInjectionId, immunization) -> {
            MedicalHealthcareVaccinationsEntity entity = convertHapiObjToTelecareObj(patientId, hmapReactionAfterInjection, reactionAfterInjectionId, immunization, hmapOrganization);
            entity.setPhoneNumber(patientInfo.getPhoneNumber());
            entity.setFullname(patientInfo.getFullname());
            hsskEntities.add(entity);
        });

        List<MedicalHealthcareVaccinationsEntity> oldEntities = medicalHealthcareVaccinationsRepositoryJPA.findByPatientIdAndIsActiveAndIsDelete(patientId, Constants.IS_ACTIVE, Constants.IS_NOT_DELETE);
        HashSet<MedicalHealthcareVaccinationsEntity> hsetHsskEntities = new HashSet<>(hsskEntities);
        FnCommon.classifyCreateOrUpdateOrDelete(hsetHsskEntities, oldEntities);
        oldEntities.addAll(hsetHsskEntities);
        medicalHealthcareVaccinationsRepositoryJPA.saveAll(oldEntities);
    }

    public void getPatientHistoryInfo(RequestSyncHsskDTO dto) throws Exception {
        String PID = dto.getPID();
        Integer patientId = dto.getPatientId();
        String token = dto.getToken();
        String url = System.getenv("HSSK_LB_URL") + "/hssk/get-patient-history-info/" + PID +
                "?from_date=" + dto.getFromDate() +
                "&to_date=" + dto.getToDate();
        String responseBody = requestDataHSSK(url, token);
        Bundle bundle = parseHL7ToObject(responseBody);

        //classifyToHmap
        List<CarePlan> carePlans = new ArrayList<>();
        HashMap<String, Encounter> hmapEncounter = new HashMap<>();
        HashMap<String, Organization> hmapOrganization = new HashMap<>();
        HashMap<String, Practitioner> hmapPractitioner = new HashMap<>();
        HashMap<String, Appointment> hmapAppointment = new HashMap<>();
        HashMap<String, Condition> hmapCondition = new HashMap<>();
        HashMap<String, List<Condition>> hmapEncCondition = new HashMap<>();
        HashMap<String, ServiceRequest> hmapServiceRequest = new HashMap<>();
        HashMap<String, Observation> hmapObservation = new HashMap<>();
        HashMap<String, MedicationAdministration> hmapMedicationAdministration = new HashMap<>();
        HashMap<String, List<MedicationAdministration>> hmapMedMedicationAdministration = new HashMap<>();
        HashMap<String, Medication> hmapMedication = new HashMap<>();
        HashMap<String, ClinicalImpression> hmapClinicalImpression = new HashMap<>();
        HashMap<String, Device> hmapDevice = new HashMap<>();

        bundle.getEntry().forEach(entry -> {
            if (entry.getResource().getResourceType().compareTo(ResourceType.CarePlan) == 0) {
                this.ToListCarePlan(carePlans, entry);
            } else if (entry.getResource().getResourceType().compareTo(ResourceType.Encounter) == 0) {
                this.ToHMapEncounter(hmapEncounter, entry);
            } else if (entry.getResource().getResourceType().compareTo(ResourceType.Organization) == 0) {
                this.ToHmapOrgnization(hmapOrganization, entry);
            } else if (entry.getResource().getResourceType().compareTo(ResourceType.Practitioner) == 0) {
                this.ToHMapPractitioner(hmapPractitioner, entry);
            } else if (entry.getResource().getResourceType().compareTo(ResourceType.Appointment) == 0) {
                this.ToHMapAppointment(hmapAppointment, entry);
            } else if (entry.getResource().getResourceType().compareTo(ResourceType.Condition) == 0) {
                this.ToHMapCondition(hmapCondition, hmapEncCondition, entry);
            } else if (entry.getResource().getResourceType().compareTo(ResourceType.ServiceRequest) == 0) {
                this.ToHMapServiceRequest(hmapServiceRequest, entry);
            } else if (entry.getResource().getResourceType().compareTo(ResourceType.Observation) == 0) {
                this.ToHMapObservation(hmapObservation, entry);
            } else if (entry.getResource().getResourceType().compareTo(ResourceType.MedicationAdministration) == 0) {
                this.ToHMapMedicationAdministration(hmapMedicationAdministration, hmapMedMedicationAdministration, entry);
            } else if (entry.getResource().getResourceType().compareTo(ResourceType.Medication) == 0) {
                this.ToHMapMedication(hmapMedication, entry);
            } else if (entry.getResource().getResourceType().compareTo(ResourceType.ClinicalImpression) == 0) {
                this.ToHMapClinicalImpression(hmapClinicalImpression, entry);
            } else if (entry.getResource().getResourceType().compareTo(ResourceType.Device) == 0) {
                this.ToHMapDevice(hmapDevice, entry);
            }
        });

        for (CarePlan carePlan : carePlans) {
            Encounter encounter = hmapEncounter.get(carePlan.getEncounter().getReference().replace("Encounter/", ""));
            String bodyPartInfoId = carePlan.getAddressesFirstRep().getReference();

            /*save MedicalHealthcareHistoriesEntity*/
            MedicalHealthcareHistoriesEntity healthcareHistoriesEntitySaved = saveMedicalHealthcareHistoriesEntity(patientId, hmapOrganization, hmapPractitioner, hmapAppointment, hmapCondition, encounter, bodyPartInfoId);

            Integer hisId = healthcareHistoriesEntitySaved.getHistoriesId();
            String healthFacilityCode = healthcareHistoriesEntitySaved.getHealthfacilitiesCode();

            /*save MedicalHealthcareHistoriesIcdEntity */
            saveMedicalHealthcareHistoriesIcdEntity(patientId, hmapEncCondition, encounter, hisId);

            /*save MedicalHealthcareDrugsEntity*/
            saveMedicalHealthcareDrugsEntity(patientId, hmapMedMedicationAdministration, hmapMedication, carePlan, hisId);

            /*save MedicalHealthcareServicesEntity*/
            HashMap<MedicalHealthcareServicesEntity, ServiceRequest> serviceInfo = saveMedicalHealthcareServicesEntity(patientId, hmapServiceRequest, hmapClinicalImpression, encounter, hisId, healthFacilityCode);

            /*save MedicalHealthcareServicesResultsEntity*/
            saveMedicalHealthcareServicesResultsEntity(hmapServiceRequest, hmapObservation, hisId, serviceInfo);

            /*save MedicalHealthcarePatientDetailEntity*/
            List<String> reasonReferences = encounter.getReasonReference().stream().map(Reference::getReference).collect(Collectors.toList());
            String commonInfoId = null;
            if(reasonReferences.size()>0) {
                for(String reasonReference : reasonReferences) {
                    if(reasonReference.contains("Observation/")) {
                        commonInfoId = reasonReference.replace("Observation/", "");
                        break;
                    }
                }
            }
            Observation commonInfo = commonInfoId!=null? hmapObservation.get(commonInfoId) : null;
            saveMedicalHealthcarePatientDetailEntity(patientId, hisId, commonInfo, hmapDevice);
        }
    }

    public void saveMedicalHealthcarePatientDetailEntity(Integer patientId, Integer hisId, Observation commonInfo, HashMap<String, Device> hmapDevice) throws NoSuchFieldException, IllegalAccessException {
        if(commonInfo==null) {
            return;
        }
        MedicalHealthcarePatientDetailEntity entity = convertHapiObjToTelecareObj(hmapDevice, commonInfo);
        entity.setPatientId(patientId);
        entity.setHistoriesId(hisId);
        MedicalHealthcarePatientDetailEntity oldEntity = medicalHealthcarePatientDetailRepositoryJPA.findByHistoriesId(hisId);
        if(oldEntity!=null) {
            entity.setCreateDate(oldEntity.getCreateDate());
            entity.setUpdateDate(new Date());
            entity.setDetailId(oldEntity.getDetailId());
        }
        medicalHealthcarePatientDetailRepositoryJPA.save(entity);
    }

    public MedicalHealthcarePatientDetailEntity convertHapiObjToTelecareObj(HashMap<String, Device> hmapDevice, Observation observation) {
        MedicalHealthcarePatientDetailEntity entity = new MedicalHealthcarePatientDetailEntity();
        this.convertToOtherIndex(hmapDevice, observation, entity);
        observation.getComponent().forEach(component -> {
            this.convertToMedicalIndex(entity, component);
        });
        entity.setIsSync(Constants.IS_SYNC_BOOLEAN);
        return entity;
    }

    public void convertToOtherIndex(HashMap<String, Device> hmapDevice, Observation observation, MedicalHealthcarePatientDetailEntity patientDetailEntity) {
        patientDetailEntity.setResultDate(observation.getEffectiveDateTimeType().getValue());
        patientDetailEntity.setSourceId(1);
        String deviceId = observation.getDevice().getReference().replace("Device/", "");
        Device device = hmapDevice.get(deviceId);
        patientDetailEntity.setDeviceName(device.getDeviceNameFirstRep().getName());
        patientDetailEntity.setDeviceImei(device.getUdiCarrierFirstRep().getCarrierHRF());
    }

    public void convertToMedicalIndex(MedicalHealthcarePatientDetailEntity patientDetailEntity, Observation.ObservationComponentComponent component) {
        String indexType = component.getCode().getCodingFirstRep().getCode();
        BigDecimal indexValue = component.getValueQuantity().getValue();
        if (Constants.HL7_HEART_BEAT_CODE.equals(indexType)) {
            patientDetailEntity.setHeartBeat(indexValue.intValue());
        } else if (Constants.HL7_BLOOD_PRESSURE_MAX_CODE.equals(indexType)) {
            patientDetailEntity.setBloodPressureMax(indexValue.intValue());
        } else if (Constants.HL7_BLOOD_PRESSURE_MIN_CODE.equals(indexType)) {
            patientDetailEntity.setBloodPressureMin(indexValue.intValue());
        } else if (Constants.HL7_PULSE_CODE.equals(indexType)) {
            patientDetailEntity.setPulse(indexValue.intValue());
        } else if (Constants.HL7_TEMPERATURE_CODE.equals(indexType)) {
            patientDetailEntity.setTemperature(indexValue.doubleValue());
        } else if (Constants.HL7_BLOOD_SUGAR_CODE.equals(indexType)) {
            patientDetailEntity.setBloodSugar(indexValue.doubleValue());
        } else if (Constants.HL7_SPO2_SCORE_CODE.equals(indexType)) {
            patientDetailEntity.setSpo2Score(indexValue.intValue());
        } else if (Constants.HL7_BREATH_CODE.equals(indexType)) {
            patientDetailEntity.setBreath(indexValue.intValue());
        } else if (Constants.HL7_WEIGHT_CODE.equals(indexType)) {
            patientDetailEntity.setWeight(indexValue.doubleValue());
        } else if (Constants.HL7_BMI_CODE.equals(indexType)) {
            patientDetailEntity.setBmi(indexValue.doubleValue());
        } else if (Constants.HL7_KCAL_CODE.equals(indexType)) {
            patientDetailEntity.setKcal(indexValue.doubleValue());
        } else if (Constants.HL7_PACE_CODE.equals(indexType)) {
            patientDetailEntity.setWalk(indexValue.intValue());
        } else if (Constants.HL7_HEIGHT_CODE.equals(indexType)) {
            patientDetailEntity.setHeight(indexValue.doubleValue());
        } else if (Constants.HL7_WAIST_CIRCUMFERENCE_CODE.equals(indexType)) {
            patientDetailEntity.setWaistCircumference(indexValue.intValue());
        }
    }

    public void saveMedicalHealthcareServicesResultsEntity(HashMap<String, ServiceRequest> hmapServiceRequest, HashMap<String, Observation> hmapObservation, Integer hisId, HashMap<MedicalHealthcareServicesEntity, ServiceRequest> hmapServiceRequests) {
        if (hmapServiceRequests == null) {
            return;
        }
        hmapServiceRequests.forEach((serviceInfo, serviceRequest) -> {
            List<MedicalHealthcareServicesResultsEntity> hsskEntities = serviceRequest.getBasedOn().stream()
                    .filter(Objects::nonNull)
                    .map(base -> convertHapiObjToTelecareObj(hmapServiceRequest, hmapObservation, hisId, base, serviceInfo))
                    .collect(Collectors.toList());

            //classify CRUD
            List<MedicalHealthcareServicesResultsEntity> oldEntities = medicalHealthcareServicesResultsRepositoryJPA.findByHistoriesIdAndIsActiveAndIsDelete(hisId, Constants.IS_ACTIVE, Constants.IS_NOT_DELETE);
            HashSet<MedicalHealthcareServicesResultsEntity> hsetHsskEntities = new HashSet<>(hsskEntities);
            try {
                FnCommon.classifyCreateOrUpdateOrDelete(hsetHsskEntities, oldEntities);
            } catch (Exception e) {
                LOGGER.info(e);
            }
            oldEntities.addAll(hsetHsskEntities);
            medicalHealthcareServicesResultsRepositoryJPA.saveAll(oldEntities);
        });
    }

    public HashMap<MedicalHealthcareServicesEntity, ServiceRequest> saveMedicalHealthcareServicesEntity(Integer patientId, HashMap<String, ServiceRequest> hmapServiceRequest, HashMap<String, ClinicalImpression> hmapClinicalImpression, Encounter encounter, Integer hisId, String healthFacilityCode) throws NoSuchFieldException, IllegalAccessException {
        List<String> serviceInfoIds = encounter.getBasedOn().stream()
                .map((baseOn) -> baseOn.getReference().replace("ServiceRequest/", ""))
                .collect(Collectors.toList());
        if (serviceInfoIds.size() == 0) {
            return null;
        }
        List<ServiceRequest> serviceRequests = serviceInfoIds.stream().map(hmapServiceRequest::get).collect(Collectors.toList());
        List<MedicalHealthcareServicesEntity> hsskEntities = serviceRequests.stream()
                .map(serviceRequest -> convertHapiObjToTelecareObj(patientId, hmapClinicalImpression, hisId, serviceRequest, healthFacilityCode))
                .collect(Collectors.toList());
        HashMap<String, ServiceRequest> hmapSerCodeSerRequest = new HashMap<>();
        serviceRequests.forEach(serviceRequest -> hmapSerCodeSerRequest.put(serviceRequest.getCode().getCodingFirstRep().getDisplay(), serviceRequest));

        //classify CRUD
        List<MedicalHealthcareServicesEntity> oldEntities = medicalHealthcareServicesRepositoryJPA.findByHistoriesIdAndIsActiveAndIsDelete(hisId, Constants.IS_ACTIVE, Constants.IS_NOT_DELETE);
        HashSet<MedicalHealthcareServicesEntity> hsetHsskEntities = new HashSet<>(hsskEntities);
        FnCommon.classifyCreateOrUpdateOrDelete(hsetHsskEntities, oldEntities);
        oldEntities.addAll(hsetHsskEntities);
        List<MedicalHealthcareServicesEntity> entitiesSaved = medicalHealthcareServicesRepositoryJPA.saveAll(oldEntities);

        //transfer serviceInfo to other method
        HashMap<MedicalHealthcareServicesEntity, ServiceRequest> hmapSerInfoSerRequests = new HashMap<>();
        entitiesSaved.forEach(entitySaved -> hmapSerInfoSerRequests.put(entitySaved, hmapSerCodeSerRequest.get(entitySaved.getServiceName())));
        return hmapSerInfoSerRequests;
    }

    public void saveMedicalHealthcareDrugsEntity(Integer patientId, HashMap<String, List<MedicationAdministration>> hmapMedMedicationAdministration, HashMap<String, Medication> hmapMedication, CarePlan carePlan, Integer hisId) throws NoSuchFieldException, IllegalAccessException {
        List<String> drugsIds = carePlan.getActivity().stream()
                .map((activity) -> activity.getDetail().getProductReference().getReference().replace("Medication/", ""))
                .collect(Collectors.toList());
        List<Medication> medications = drugsIds.stream().map(hmapMedication::get).collect(Collectors.toList());
        List<MedicalHealthcareDrugsEntity> hsskEntities = medications.stream()
                .map((medication) -> convertHapiObjToTelecareObj(patientId, hmapMedMedicationAdministration, hisId, medication))
                .collect(Collectors.toList());

        //classify CRUD
        List<MedicalHealthcareDrugsEntity> oldEntities = medicalHealthcareDrugsRepositoryJPA.findByHistoriesIdAndIsActiveAndIsDelete(hisId, Constants.IS_ACTIVE, Constants.IS_NOT_DELETE);
        HashSet<MedicalHealthcareDrugsEntity> hsetHsskEntities = new HashSet<>(hsskEntities);
        FnCommon.classifyCreateOrUpdateOrDelete(hsetHsskEntities, oldEntities);
        oldEntities.addAll(hsetHsskEntities);
        medicalHealthcareDrugsRepositoryJPA.saveAll(oldEntities);
    }

    public void saveMedicalHealthcareHistoriesIcdEntity(Integer patientId, HashMap<String, List<Condition>> hmapEncCondition, Encounter encounter, Integer hisId) throws NoSuchFieldException, IllegalAccessException {
        String encounterId = encounter.getId().replace("urn:uuid:", "");
        List<Condition> conditions = hmapEncCondition.get(encounterId);
        if (conditions != null) {
            List<MedicalHealthcareHistoriesIcdEntity> hsskEntities = new ArrayList<>();
            conditions.forEach((condition) -> {
                MedicalHealthcareHistoriesIcdEntity medicalHealthcareHistoriesIcdEntity = convertHapiObjToTelecareObj(patientId, hisId, condition);
                hsskEntities.add(medicalHealthcareHistoriesIcdEntity);
            });

            //classify CRUD
            List<MedicalHealthcareHistoriesIcdEntity> oldEntities = medicalHealthcareHistoriesIcdRepositoryJPA.findByHistoriesIdAndIsActiveAndIsDelete(hisId, Constants.IS_ACTIVE, Constants.IS_NOT_DELETE);
            HashSet<MedicalHealthcareHistoriesIcdEntity> hsetHsskEntities = new HashSet<>(hsskEntities);
            FnCommon.classifyCreateOrUpdateOrDelete(hsetHsskEntities, oldEntities);
            oldEntities.addAll(hsetHsskEntities);
            medicalHealthcareHistoriesIcdRepositoryJPA.saveAll(oldEntities);
        }
    }

    public MedicalHealthcareHistoriesEntity saveMedicalHealthcareHistoriesEntity(Integer patientId, HashMap<String, Organization> hmapOrganization, HashMap<String, Practitioner> hmapPractitioner, HashMap<String, Appointment> hmapAppointment, HashMap<String, Condition> hmapCondition, Encounter encounter, String bodyPartInfoId) throws NoSuchFieldException, IllegalAccessException {
        bodyPartInfoId = bodyPartInfoId!=null? bodyPartInfoId.replace("Condition/", "") : null;
        MedicalHealthcareHistoriesEntity hsskEntity = convertHapiObjToTelecareObj(encounter, hmapOrganization, hmapPractitioner, hmapAppointment, hmapCondition, bodyPartInfoId, patientId);
        List<MedicalHealthcareHistoriesEntity> oldEntities = medicalHealthcareHistoriesRepositoryJPA.findByPatientIdAndIsActiveAndIsDelete(patientId, Constants.IS_ACTIVE, Constants.IS_NOT_DELETE);
        MedicalHealthcareHistoriesEntity entity = (MedicalHealthcareHistoriesEntity) FnCommon.classifyCreateOrUpdate(hsskEntity, oldEntities);
        return medicalHealthcareHistoriesRepositoryJPA.save(entity);
    }

    public MedicalHealthcareServicesEntity convertHapiObjToTelecareObj(Integer patientId, HashMap<String, ClinicalImpression> hmapClinicalImpression, Integer hisId, ServiceRequest serviceRequest, String healthFacilityCode) {
        MedicalHealthcareServicesEntity entity = new MedicalHealthcareServicesEntity();
        entity.setServiceName(serviceRequest.getCode().getCodingFirstRep().getDisplay());
        entity.setServiceCode(serviceRequest.getCode().getCodingFirstRep().getCode());
        entity.setPatientId(patientId);
        entity.setHistoriesId(hisId);
        entity.setHealthfacilitiesCode(healthFacilityCode);
        String groupId = serviceRequest.getCategoryFirstRep().getCodingFirstRep().getCode();
        //hssk can return serviceGroupCode = null
        entity.setGroupId(groupId!=null? Integer.parseInt(serviceRequest.getCategoryFirstRep().getCodingFirstRep().getCode()) : 1);
        entity.setUnit(serviceRequest.getQuantityQuantity().getUnit());
        if (serviceRequest.getQuantityQuantity().getValue() != null) {
            entity.setQuantity(serviceRequest.getQuantityQuantity().getValue().intValue());
        }
        entity.setResults(serviceRequest.getReasonCodeFirstRep().getCodingFirstRep().getDisplay());
        entity.setSuggestions(serviceRequest.getPatientInstruction());
        entity.setDecisionDate(serviceRequest.getOccurrenceDateTimeType().getValue());
        String conclusionId = serviceRequest.getSupportingInfoFirstRep().getReference().replace("ClinicalImpression/", "");
        entity.setConcludes(hmapClinicalImpression.get(conclusionId).getSummary());
        entity.setIsSync(Constants.IS_SYNC);
        return entity;
    }

    public MedicalHealthcareServicesResultsEntity convertHapiObjToTelecareObj(HashMap<String, ServiceRequest> hmapServiceRequest, HashMap<String, Observation> hmapObservation, Integer hisId, Reference base, MedicalHealthcareServicesEntity serviceInfo) {
        String id = base.getReference().replace("ServiceRequest/", "");
        ServiceRequest serRequest = hmapServiceRequest.get(id);
        MedicalHealthcareServicesResultsEntity entity = new MedicalHealthcareServicesResultsEntity();
        entity.setHistoriesId(hisId);
        entity.setIndexName(serRequest.getCode().getCodingFirstRep().getDisplay());
        entity.setSercive(serviceInfo);
        entity.setValue(String.valueOf(serRequest.getQuantityQuantity().getValue().intValue()));
        entity.setIndexCode(serRequest.getCode().getCodingFirstRep().getCode());
        entity.setUnit(serRequest.getQuantityQuantity().getUnit());
        String limitId = serRequest.getReasonReferenceFirstRep().getReference().replace("Observation/", "");
        Observation limit = hmapObservation.get(limitId);
        String limitMin = limit.getValueRange().getLow().getValue().toString();
        String limitMax = limit.getValueRange().getHigh().getValue().toString();
        entity.setLimit(limitMin + "," + limitMax);
        entity.setIsSync(Constants.IS_SYNC);
        return entity;
    }

//    public static void main(String[] args) {
//        HSSKService hsskService = new HSSKService();
//        try {
//            hsskService.getPatientHistoryInfo("1", 151);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public MedicalHealthcareDrugsEntity convertHapiObjToTelecareObj(Integer patientId, HashMap<String, List<MedicationAdministration>> hmapMedMedicationAdministration, Integer hisId, Medication medication) {
        MedicalHealthcareDrugsEntity drugsEntity = new MedicalHealthcareDrugsEntity();
        drugsEntity.setDrugCode(medication.getCode().getCodingFirstRep().getCode());
        drugsEntity.setDrugName(medication.getCode().getCodingFirstRep().getDisplay());
        drugsEntity.setPatientId(patientId);
        drugsEntity.setHistoriesId(hisId);
        drugsEntity.setIsSync(Constants.IS_SYNC);
        if (medication.getIngredientFirstRep().getStrength().getNumerator().getValue() != null) {
            drugsEntity.setQuantity(medication.getIngredientFirstRep().getStrength().getNumerator().getValue().intValue());
        }
        List<MedicationAdministration> medicationAdministrations =
                hmapMedMedicationAdministration.get(medication.getId().replace("urn:uuid:", ""));
        for(MedicationAdministration medicationAdministration : medicationAdministrations) {
            this.getDosage(drugsEntity, medicationAdministration);
            this.getOthersMedicationsAdministration(drugsEntity, medicationAdministration);
        }
        return drugsEntity;
    }

    public void getOthersMedicationsAdministration(MedicalHealthcareDrugsEntity drugsEntity, MedicationAdministration medicationAdministration) {
        if (medicationAdministration.getDosage().getDose().getUnit() != null) {
            drugsEntity.setUnit(medicationAdministration.getDosage().getDose().getUnit());
        }
        if (medicationAdministration.getDosage().getRoute().getCodingFirstRep().getDisplay() != null) {
            String methodName = medicationAdministration.getDosage().getRoute().getCodingFirstRep().getDisplay();
            String methodCode = catsMethodsRepositoryJPA.findMethodCodeByMethodName(methodName);
            drugsEntity.setMethodCode(methodCode);
        }
        if (medicationAdministration.getDosage().getText() != null) {
            drugsEntity.setDosageDescription(medicationAdministration.getDosage().getText());
        }
        if (medicationAdministration.getEffectiveDateTimeType().getValue() != null) {
            drugsEntity.setDecisionDate(FnCommon.toLocalDateTime(medicationAdministration.getEffectiveDateTimeType().getValue()));
        }
        if(medicationAdministration.getNoteFirstRep().getText()!=null) {
            drugsEntity.setNotes(medicationAdministration.getNoteFirstRep().getText());
        }
    }

    public void getDosage(MedicalHealthcareDrugsEntity drugsEntity, MedicationAdministration medicationAdministration) {
        if ("http://dmdc.kcb.vn/CodeSystem/Thuoc/ThoiGIanDung".equals(medicationAdministration.getCategory().getCodingFirstRep().getSystem())) {
            switch (medicationAdministration.getCategory().getCodingFirstRep().getCode()) {
                case "1":
                    drugsEntity.setMorningAmount(medicationAdministration.getDosage().getRateQuantity().getValue().intValue());
                    break;
                case "2":
                    drugsEntity.setNoonAmount(medicationAdministration.getDosage().getRateQuantity().getValue().intValue());
                    break;
                case "3":
                    drugsEntity.setAfternoonAmount(medicationAdministration.getDosage().getRateQuantity().getValue().intValue());
                    break;
                case "4":
                    drugsEntity.setEveningAmount(medicationAdministration.getDosage().getRateQuantity().getValue().intValue());
                    break;
            }
        }
    }

    public MedicalHealthcareHistoriesIcdEntity convertHapiObjToTelecareObj(Integer patientId, Integer hisId, Condition condition) {
        MedicalHealthcareHistoriesIcdEntity entity = new MedicalHealthcareHistoriesIcdEntity();
        entity.setPatientId(patientId);
        entity.setHistoriesId(hisId);
        entity.setDiseasesCode(condition.getCode().getCodingFirstRep().getCode());
        entity.setDiseasesName(condition.getCode().getCodingFirstRep().getDisplay());
        entity.setIsMain(0);
        entity.setIsSync(Constants.IS_SYNC);
        return entity;
    }

    public MedicalHealthcareHistoriesEntity convertHapiObjToTelecareObj(Encounter encounter,
                                                                        HashMap<String, Organization> hmapOrganization,
                                                                        HashMap<String, Practitioner> hmapPractitioner,
                                                                        HashMap<String, Appointment> hmapAppointment,
                                                                        HashMap<String, Condition> hmapCondition,
                                                                        String bodyPartInfoId,
                                                                        Integer patientId) {

        MedicalHealthcareHistoriesEntity entity = new MedicalHealthcareHistoriesEntity();

        entity.setPatientId(patientId);
        entity.setExaminationDate(encounter.getPeriod().getStart());
        entity.setFinishExaminationDate(encounter.getPeriod().getEnd());
        entity.setReasonsMedicalexamination(encounter.getReasonCodeFirstRep().getText());

        String organizationId = encounter.getHospitalization().getOrigin().getReference().replace("Organization/", "");
        String healthFacilityCode = hmapOrganization.get(organizationId).getIdentifierFirstRep().getValue();
        entity.setHealthfacilitiesCode(healthFacilityCode);
        CatsHealthfacilitiesEntity healthFacility = catsHealthfacilitiesRepositoryJPA.findFirstByHealthfacilitiesCode(healthFacilityCode);
        entity.setHealthfacilitiesName(healthFacility!=null? healthFacility.getName() : null);

        String practitionerId = encounter.getParticipantFirstRep().getIndividual().getReference().replace("Practitioner/", "");
        String doctorName = hmapPractitioner.get(practitionerId).getNameFirstRep().getText();
        entity.setDoctorName(doctorName);

        String reExaminationDateId = encounter.getAppointmentFirstRep().getReference().replace("Appointment/", "");
        Date reExaminationDate = hmapAppointment.get(reExaminationDateId).getRequestedPeriodFirstRep().getStart();
        entity.setReExaminationDate(reExaminationDate == null ? null : FnCommon.toLocalDateTime(reExaminationDate));

        String symptomId = encounter.getReasonReferenceFirstRep().getReference().replace("Condition/", "");
        String symtoms = hmapCondition.get(symptomId).getStageFirstRep().getSummary().getCodingFirstRep().getDisplay();
        entity.setSymptoms(symtoms);

        String concludeDiseaseId = encounter.getDiagnosisFirstRep().getCondition().getReference().replace("Condition/", "");
        String concludeDisease = hmapCondition.get(concludeDiseaseId).getStageFirstRep().getSummary().getCodingFirstRep().getDisplay();
        entity.setConcludesDisease(concludeDisease != null ? concludeDisease : "N/A");

        //HSSK chưa có digitalSign
        entity.setDigitalSignatures("N/A");

        String typeOfExamination = encounter.getExtensionByUrl(Constants.HL7_EXT_URL_EXAM_TYPE).getValue().toString();
        entity.setTypeOfExamination(FnCommon.toExamTypeId(typeOfExamination));
        entity.setIsSync(Constants.IS_SYNC);
        Extension extTreatment = encounter.getExtensionByUrl("http://dmdc.kcb.vn/extension/huongdieutri");
        entity.setTreatmentDirection(extTreatment!=null? extTreatment.getValue().toString():null);

        //bodyPart
        if(bodyPartInfoId!=null) {
            Condition bodyPartInfo = hmapCondition.get(bodyPartInfoId);
            Extension extRightEyeWithGlasses = bodyPartInfo.getExtensionByUrl(Constants.HL7_EXT_URL_RIGHT_EYE_GLASS);
            entity.setRightEyeWithGlasses(extRightEyeWithGlasses != null ? extRightEyeWithGlasses.getValue().toString() : null);
            Extension extRightEyeWithoutGlasses = bodyPartInfo.getExtensionByUrl(Constants.HL7_EXT_URL_RIGHT_EYE_NO_GLASS);
            entity.setRightEyeWithoutGlasses(extRightEyeWithoutGlasses != null ? extRightEyeWithoutGlasses.getValue().toString() : null);
            Extension extLeftEyeWithGlasses = bodyPartInfo.getExtensionByUrl(Constants.HL7_EXT_URL_LEFT_EYE_GLASS);
            entity.setLeftEyeWithGlasses(extLeftEyeWithGlasses != null ? extLeftEyeWithGlasses.getValue().toString() : null);
            Extension extLeftEyeWithoutGlasses = bodyPartInfo.getExtensionByUrl(Constants.HL7_EXT_URL_LEFT_EYE_NO_GLASS);
            entity.setLeftEyeWithoutGlasses(extLeftEyeWithoutGlasses != null ? extLeftEyeWithoutGlasses.getValue().toString() : null);

            List<CodeableConcept> bodySites = bodyPartInfo.getBodySite();

            if (bodySites != null && bodySites.size() > 0) {
                bodySites.stream().filter(bodySite -> Objects.nonNull(bodySite.getCoding()))
                        .forEach(bodySite -> {
                            String key = bodySite.getCodingFirstRep().getCode();
                            String value = bodySite.getCodingFirstRep().getDisplay();
                            if ("BODY_SKIN".equals(key)) {
                                entity.setBodySkin(value);
                            } else if ("BODY_EXAMINATION_OTHERS".equals(key)) {
                                entity.setBodySkinOther(value);
                            } else if ("BODY_HEARTS".equals(key)) {
                                entity.setBodyPartHeart(value);
                            } else if ("BODY_RESPIRATORIES".equals(key)) {
                                entity.setBodyPartRespiratory(value);
                            } else if ("BODY_DIGESTIVES".equals(key)) {
                                entity.setBodyPartDigest(value);
                            } else if ("BODY_URINARIES".equals(key)) {
                                entity.setBodyPartUrinary(value);
                            } else if ("BODY_MUSCULOSKELETAL_SYSTEM".equals(key)) {
                                entity.setBodyPartOsteoarthritis(value);
                            } else if ("BODY_ENDOCRINES".equals(key)) {
                                entity.setBodyPartEndocrine(value);
                            } else if ("BODY_NERVES".equals(key)) {
                                entity.setBodyPartNerve(value);
                            } else if ("BODY_MENTALS".equals(key)) {
                                entity.setBodyPartMental(value);
                            } else if ("BODY_EAR_NOSE_THROATS".equals(key)) {
                                entity.setBodyPartEarNoseThroat(value);
                            } else if ("BODY_DENTOMAXILLOFACIALS".equals(key)) {
                                entity.setBodyPartDentomaxillofacial(value);
                            } else if ("BODY_EYES".equals(key)) {
                                entity.setBodyPartEye(value);
                            } else if ("BODY_NUTRITIONS".equals(key)) {
                                entity.setBodyPartNutrition(value);
                            } else if ("BODY_MOVEMENTS".equals(key)) {
                                entity.setBodyPartMotive(value);
                            } else if ("BODY_OTHERS".equals(key)) {
                                entity.setBodyPartOther(value);
                            } else if ("EVALUATIONS".equals(key)) {
                                entity.setBodyPartEvaluation(value);
                            }
                        });
            }
        }
        return entity;
    }

    public Bundle parseHL7ToObject(String responseBody) {
        FhirContext ctx = FhirContext.forR4();
        IParser parser = ctx.newXmlParser();
        return parser.parseResource(Bundle.class, responseBody);
    }

    public String requestDataHSSK(String url, String token) throws IOException {
//        System.out.println(token);
//        System.out.println(url);
        Response response = FnCommon.doGetRequest(url, token);
        assert response != null;
        String responseBody = response.body().string();
        System.out.println(responseBody);
        return responseBody;
    }

    public MedicalHealthcarePresurgeryEntity convertHapiObjToTelecareObj(Procedure procedure,
                                                                         HashMap<String, String> hmapOrganizationCode,
                                                                         Integer patientId,
                                                                         String healthfacilitiesUUID) {
        MedicalHealthcarePresurgeryEntity entity = new MedicalHealthcarePresurgeryEntity();
        entity.setSurgicalSurgeryName(procedure.getBodySiteFirstRep().getCodingFirstRep().getDisplay());
        entity.setYearOfSurgery(Integer.parseInt(procedure.getPerformed().toString()));
        entity.setReasonOfSurgery(procedure.getOutcome().getText());
        entity.setSideEffectOfSurgery(procedure.getComplicationFirstRep().getCodingFirstRep().getDisplay());
        entity.setHealthfacilitiesCode(hmapOrganizationCode.get(healthfacilitiesUUID));
        entity.setPatientId(patientId);
        entity.setRelationshipId(1);
        entity.setIsSync(Constants.IS_SYNC);
        return entity;
    }

    public MedicalHealthcarePrehistoricEntity convertHapiObjToTelecareObj(Integer patientId, Condition condition) {
        MedicalHealthcarePrehistoricEntity entity = new MedicalHealthcarePrehistoricEntity();
        entity.setPatientId(patientId);
        entity.setRelationshipId(1);
        entity.setHealthHistory(condition.getCode().getCoding().get(0).getDisplay());
        entity.setIsSync(Constants.IS_SYNC);
        return entity;
    }

    public MedicalHealthcarePrehistoricEntity convertHapiObjToTelecareObj(Integer patientId, FamilyMemberHistory familyMemberHistory) {
        MedicalHealthcarePrehistoricEntity entity = new MedicalHealthcarePrehistoricEntity();
        entity.setPatientId(patientId);
        entity.setHealthHistory(familyMemberHistory.getConditionFirstRep().getCode().getCodingFirstRep().getDisplay());
        String relationship = familyMemberHistory.getRelationship().getText();
        CatsRelationshipsEntity catsRelationshipsEntity = catsRelationshipsRepositoryJPA.findFirstByNameAndIsActiveAndIsDelete(relationship, Constants.IS_ACTIVE, Constants.IS_NOT_DELETE);
        Integer relationshipId = catsRelationshipsEntity != null ? catsRelationshipsEntity.getRelationshipId() : Constants.RELATIONSHIP_ID_OTHERS;
        entity.setRelationshipId(relationshipId);
        entity.setIsSync(Constants.IS_SYNC);
        return entity;
    }

    public MedicalHealthcareAllergyEntity convertHapiObjToTelecareObj(Integer patientId, AllergyIntolerance allergyIntolerance) {
        MedicalHealthcareAllergyEntity entity = new MedicalHealthcareAllergyEntity();
        entity.setAllergyName(allergyIntolerance.getCode().getCodingFirstRep().getDisplay());
        entity.setAllergyExpression(allergyIntolerance.getReactionFirstRep().getDescription());
        entity.setPatientId(patientId);
        entity.setRelationshipId(1);
        entity.setIsSync(Constants.IS_SYNC);
//        Extension allergyGroupExt = allergyIntolerance.getExtensionByUrl(Constants.HL7_EXT_URL_ALLERGY_GROUP);
//        entity.setAllergyGroupId(allergyGroupExt==null? null : Integer.parseInt(allergyGroupExt.getValue().toString()));
        return entity;
    }

    public MedicalHealthcareVaccinationsEntity convertHapiObjToTelecareObj(Integer patientId, HashMap<String, Observation> hmapReactionAfterInjection, String reactionAfterInjectionId, Immunization immunization, HashMap<String, Organization> hmapOrganization) {
//        String status = immunization.getStatus().toCode();
        MedicalHealthcareVaccinationsEntity entity = new MedicalHealthcareVaccinationsEntity();
        entity.setPatientId(patientId);
        entity.setVaccineName(immunization.getVaccineCode().getCodingFirstRep().getDisplay());
        entity.setAntigen(immunization.getProtocolAppliedFirstRep().getTargetDiseaseFirstRep().getCodingFirstRep().getDisplay());
        entity.setInjectionsNumber(immunization.getProtocolAppliedFirstRep().getDoseNumberPositiveIntType().getValue());
        entity.setInjectionDate(immunization.getOccurrenceDateTimeType().getValue());
        Observation reaction = hmapReactionAfterInjection.get(reactionAfterInjectionId);
        entity.setReactionLevel(reaction!=null? reaction.getValue().toString() : null);
        entity.setReactionTime(reaction!=null? reaction.getEffectiveDateTimeType().getValueAsString() : null);
        if (Objects.nonNull(immunization.getPerformerFirstRep().getActor())) {
            String healthFacilitiesId = immunization.getPerformerFirstRep().getActor().getReference().replace("Organization/", "");
            String healthFacilitiesCode = hmapOrganization.get(healthFacilitiesId).getIdentifierFirstRep().getValue();
            String healthFacilitiesname = hmapOrganization.get(healthFacilitiesId).getName();
            entity.setHealthfacilitiesCode(healthFacilitiesCode);
            entity.setVaccinationFacility(healthFacilitiesname);
        } else {
            entity.setHealthfacilitiesCode("N/A");
            entity.setVaccinationFacility("N/A");
        }
        entity.setBatchNumber(immunization.getLotNumber());
        Extension resultExt = immunization.getExtensionByUrl(Constants.HL7_EXT_URL_VACCINATION_RESULT);
        entity.setResult(resultExt==null?null : resultExt.getValue().toString());
        entity.setIsSync(Constants.IS_SYNC);
        return entity;
    }


    public void ToHMapMedicationAdministration(HashMap<String, MedicationAdministration> hmapMedicationAdministration, HashMap<String, List<MedicationAdministration>> hmapMedMedicationAdministration, Bundle.BundleEntryComponent entry) {
        MedicationAdministration medicationAdministration = (MedicationAdministration) entry.getResource();
        String id = medicationAdministration.getId().replace("urn:uuid:", "");
        hmapMedicationAdministration.put(id, medicationAdministration);

        if (medicationAdministration.getMedicationReference().getReference() != null) {
            String medicationId = medicationAdministration.getMedicationReference().getReference().replace("Medication/", "");
            List<MedicationAdministration> list = hmapMedMedicationAdministration.get(medicationId) == null ? new ArrayList<>() : hmapMedMedicationAdministration.get(medicationId);
            list.add(medicationAdministration);
            hmapMedMedicationAdministration.put(medicationId, list);
        }
    }

    public void ToHMapClinicalImpression(HashMap<String, ClinicalImpression> hmapClinicalImpression, Bundle.BundleEntryComponent entry) {
        ClinicalImpression clinicalImpression = (ClinicalImpression) entry.getResource();
        String id = clinicalImpression.getId().replace("urn:uuid:", "");
        hmapClinicalImpression.put(id, clinicalImpression);
    }

    public void ToHMapMedication(HashMap<String, Medication> hmapMedication, Bundle.BundleEntryComponent entry) {
        Medication medication = (Medication) entry.getResource();
        String id = medication.getId().replace("urn:uuid:", "");
        hmapMedication.put(id, medication);
    }

    public void ToHMapDevice(HashMap<String, Device> hmapDevice, Bundle.BundleEntryComponent entry) {
        Device device = (Device) entry.getResource();
        String id = device.getId().replace("urn:uuid:", "");
        hmapDevice.put(id, device);
    }

    public void ToHMapObservation(HashMap<String, Observation> hmapObservation, Bundle.BundleEntryComponent entry) {
        Observation observation = (Observation) entry.getResource();
        String id = observation.getId().replace("urn:uuid:", "");
        hmapObservation.put(id, observation);
    }

    public void ToHMapServiceRequest(HashMap<String, ServiceRequest> hmapServiceRequest, Bundle.BundleEntryComponent entry) {
        ServiceRequest serviceRequest = (ServiceRequest) entry.getResource();
        String id = serviceRequest.getId().replace("urn:uuid:", "");
        hmapServiceRequest.put(id, serviceRequest);
    }

    public void ToHMapCondition(HashMap<String, Condition> hmapCondition, HashMap<String, List<Condition>> hmapEncCondition, Bundle.BundleEntryComponent entry) {
        Condition condition = (Condition) entry.getResource();
        String id = condition.getId().replace("urn:uuid:", "");
        hmapCondition.put(id, condition);

        if (condition.getEncounter().getReference() != null) {
            String encounterId = condition.getEncounter().getReference().replace("Encounter/", "");
            List<Condition> listCondition = hmapEncCondition.get(encounterId) == null ? new ArrayList<>() : hmapEncCondition.get(encounterId);
            listCondition.add(condition);
            hmapEncCondition.put(encounterId, listCondition);
        }
    }

    public void ToHMapAppointment(HashMap<String, Appointment> hmapAppointment, Bundle.BundleEntryComponent entry) {
        Appointment appointment = (Appointment) entry.getResource();
        String id = appointment.getId().replace("urn:uuid:", "");
        hmapAppointment.put(id, appointment);
    }

    public void ToHMapPractitioner(HashMap<String, Practitioner> hmapPractitioner, Bundle.BundleEntryComponent entry) {
        Practitioner practitioner = (Practitioner) entry.getResource();
        String id = practitioner.getId().replace("urn:uuid:", "");
        hmapPractitioner.put(id, practitioner);
    }

    public void ToHmapOrgnization(HashMap<String, Organization> hmapOrganization, Bundle.BundleEntryComponent entry) {
        Organization organization = (Organization) entry.getResource();
        String id = organization.getId().replace("urn:uuid:", "");
        hmapOrganization.put(id, organization);
    }

    public void ToHMapEncounter(HashMap<String, Encounter> hmapEncounter, Bundle.BundleEntryComponent entry) {
        Encounter encounter = (Encounter) entry.getResource();
        String id = encounter.getId().replace("urn:uuid:", "");
        hmapEncounter.put(id, encounter);
    }

    public void ToListCarePlan(List<CarePlan> carePlans, Bundle.BundleEntryComponent entry) {
        CarePlan carePlan = (CarePlan) entry.getResource();
        carePlans.add(carePlan);
    }

    public PatientDTO getPatientInfo(Authentication authentication, String language) throws TeleCareException {
        TelecareUserEntity telecareUserEntity = FnCommon.getTelecareUserInfo(authentication);
        if (telecareUserEntity == null || !telecareUserEntity.isPatient()) {
            throw new TeleCareException(ErrorApp.ERR_USER_PERMISSION, messageService.getMessage(Constants.ERR_USER_PERMISSION, language));
        }
        int patientId = Math.toIntExact(telecareUserEntity.getTelecareUserId());
        PatientsEntity patientsEntity = patientsRepositoryJPA.findByPatientIdAndIsActiveAndIsDelete(patientId, Constants.IS_ACTIVE, Constants.IS_NOT_DELETE);
        if (patientsEntity == null) {
            throw new TeleCareException(ErrorApp.ERR_DATA_PATIENT_NOT_EXIST, messageService.getMessage(Constants.ERR_DATA_PATIENT_NOT_EXIST, language));
        }
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setPatientId(patientsEntity.getPatientId());
        patientDTO.setPid(patientsEntity.getPid());
        patientDTO.setFullname(patientsEntity.getFullname());
        patientDTO.setPhoneNumber(patientsEntity.getPhoneNumber());
        return patientDTO;
    }
}
