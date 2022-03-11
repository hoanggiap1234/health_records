package com.viettel.etc.services;

import com.viettel.etc.dto.PatientDTO;
import com.viettel.etc.dto.RequestSyncHsskDTO;
import com.viettel.etc.repositories.tables.*;
import com.viettel.etc.repositories.tables.entities.MedicalHealthcareHistoriesEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

class HSSKServiceTest {

    @Mock
    private MedicalHealthcarePrehistoricRepositoryJPA medicalHealthcarePrehistoricRepositoryJPA;

    @Mock
    private CatsRelationshipsRepositoryJPA catsRelationshipsRepositoryJPA;

    @Mock
    private MedicalHealthcareAllergyRepositoryJPA medicalHealthcareAllergyRepositoryJPA;

    @Mock
    private MedicalHealthcarePresurgeryRepositoryJPA medicalHealthcarePresurgeryRepositoryJPA;

    @Mock
    private MedicalHealthcareVaccinationsRepositoryJPA medicalHealthcareVaccinationsRepositoryJPA;

    @Mock
    private MedicalHealthcareHistoriesRepositoryJPA medicalHealthcareHistoriesRepositoryJPA;

    @Mock
    private MedicalHealthcareHistoriesIcdRepositoryJPA medicalHealthcareHistoriesIcdRepositoryJPA;

    @Mock
    private MedicalHealthcareDrugsRepositoryJPA medicalHealthcareDrugsRepositoryJPA;

    @Mock
    private MedicalHealthcareServicesRepositoryJPA medicalHealthcareServicesRepositoryJPA;

    @Mock
    private MedicalHealthcareServicesResultsRepositoryJPA medicalHealthcareServicesResultsRepositoryJPA;

    @Mock
    private MedicalHealthcarePatientDetailRepositoryJPA medicalHealthcarePatientDetailRepositoryJPA;

    @Mock
    private CatsHealthfacilitiesRepositoryJPA catsHealthfacilitiesRepositoryJPA;

    @Mock
    private CatsMethodsRepositoryJPA catsMethodsRepositoryJPA;

    @InjectMocks
    HSSKService service;

    @BeforeEach
    void setUp() {
        service = new HSSKService();
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void getDiseaseHistoryInfo() throws Exception {
        service = Mockito.spy(new HSSKService(){
            @Override
            public String requestDataHSSK(String url, String token) throws IOException {
                return "<Bundle>\n" +
                        "   <entry>\n" +
                        "      <fullUrl value=\"urn:uuid:24227148-2abb-4229-950c-743258c9f082\"/>\n" +
                        "      <resource>\n" +
                        "         <Patient>\n" +
                        "            <id value=\"24227148-2abb-4229-950c-743258c9f082\"/>\n" +
                        "            <identifier>\n" +
                        "               <type>\n" +
                        "                  <coding>\n" +
                        "                     <system value=\"http://hssk.kcb.vn/identifier\"/>\n" +
                        "                     <code value=\"0100003270E\"/>\n" +
                        "                  </coding>\n" +
                        "               </type>\n" +
                        "            </identifier>\n" +
                        "            <name>\n" +
                        "               <text value=\"Nguyễn Văn A\"/>\n" +
                        "            </name>\n" +
                        "            <birthDate value=\"2000-04-02\"/>\n" +
                        "            <address>\n" +
                        "               <text value=\"Thị trấn Bắc Hà, Huyện Bắc Hà, Tỉnh Lào Cai\"/>\n" +
                        "            </address>\n" +
                        "         </Patient>\n" +
                        "      </resource>\n" +
                        "   </entry>\n" +
                        "   <entry>\n" +
                        "      <fullUrl value=\"urn:uuid:fccfc0b0-7e66-44a7-96bc-4d1e10aa56a6\"/>\n" +
                        "      <resource>\n" +
                        "         <Condition>\n" +
                        "            <id value=\"fccfc0b0-7e66-44a7-96bc-4d1e10aa56a6\"/>\n" +
                        "            <code>\n" +
                        "               <coding>\n" +
                        "                  <code value=\"A00\"/>\n" +
                        "                  <display value=\"Bệnh tả\"/>\n" +
                        "               </coding>\n" +
                        "            </code>\n" +
                        "            <onsetString value=\"1\"/>\n" +
                        "            <note>\n" +
                        "               <text value=\"1\"/>\n" +
                        "            </note>\n" +
                        "         </Condition>\n" +
                        "      </resource>\n" +
                        "   </entry>\n" +
                        "</Bundle>\n";
            }
        });
        MockitoAnnotations.initMocks(this);
        String PID ="0100003270E";
        Integer patientId = 1;
        String token = "token";
        String phoneNumber = "0909090909";
        RequestSyncHsskDTO dto = new RequestSyncHsskDTO(1L, 2L, PID, token, patientId,phoneNumber);
        //call service
        service.getDiseaseHistoryInfo(dto);
    }

    @Test
    void getDiseaseHistoryFamilyInfo() throws Exception {
        service = Mockito.spy(new HSSKService(){
            @Override
            public String requestDataHSSK(String url, String token) throws IOException {
                return "<Bundle>\n" +
                        "   <entry>\n" +
                        "      <fullUrl value=\"urn:uuid:9730a5fd-b1a9-4ae7-a994-7d998c15528b\"/>\n" +
                        "      <resource>\n" +
                        "         <Patient>\n" +
                        "            <id value=\"9730a5fd-b1a9-4ae7-a994-7d998c15528b\"/>\n" +
                        "            <identifier>\n" +
                        "               <type>\n" +
                        "                  <coding>\n" +
                        "                     <system value=\"http://hssk.kcb.vn/identifier\"/>\n" +
                        "                     <code value=\"0100003270E\"/>\n" +
                        "                  </coding>\n" +
                        "               </type>\n" +
                        "            </identifier>\n" +
                        "            <name>\n" +
                        "               <text value=\"Nguyễn Văn A\"/>\n" +
                        "            </name>\n" +
                        "            <gender value=\"female\"/>\n" +
                        "            <birthDate value=\"2000-04-02\"/>\n" +
                        "            <address>\n" +
                        "               <text value=\"Thị trấn Bắc Hà, Huyện Bắc Hà, Tỉnh Lào Cai\"/>\n" +
                        "            </address>\n" +
                        "         </Patient>\n" +
                        "      </resource>\n" +
                        "   </entry>\n" +
                        "   <entry>\n" +
                        "      <fullUrl value=\"urn:uuid:b1727080-e629-483f-bb47-7207590797ff\"/>\n" +
                        "      <resource>\n" +
                        "         <FamilyMemberHistory>\n" +
                        "            <id value=\"b1727080-e629-483f-bb47-7207590797ff\"/>\n" +
                        "            <condition>\n" +
                        "               <code>\n" +
                        "                  <coding>\n" +
                        "                     <code value=\"12\"/>\n" +
                        "                     <display value=\"Viêm ruột do Salmonella\"/>\n" +
                        "                  </coding>\n" +
                        "               </code>\n" +
                        "               <onsetString value=\"2021\"/>\n" +
                        "            </condition>\n" +
                        "            <relationship>\n" +
                        "               <text value=\"Anh của vợ\"/>\n" +
                        "            </relationship>\n" +
                        "         </FamilyMemberHistory>\n" +
                        "      </resource>\n" +
                        "   </entry>\n" +
                        "</Bundle>\n";
            }
        });
        MockitoAnnotations.initMocks(this);
        String PID ="0100003270E";
        Integer patientId = 1;
        String token = "token";
        String phoneNumber = "0909090909";
        RequestSyncHsskDTO dto = new RequestSyncHsskDTO(1L, 2L, PID, token, patientId,phoneNumber);
        //call service
        service.getDiseaseHistoryFamilyInfo(dto);
    }

    @Test
    void getMedicalAllergyInfo() throws Exception {
        service = Mockito.spy(new HSSKService(){
            @Override
            public String requestDataHSSK(String url, String token) throws IOException {
                return "<Bundle>\n" +
                        "   <entry>\n" +
                        "      <fullUrl value=\"urn:uuid:6ef516c4-b169-4e9d-80d2-6913f2c88ed4\"/>\n" +
                        "      <resource>\n" +
                        "         <Patient>\n" +
                        "            <id value=\"6ef516c4-b169-4e9d-80d2-6913f2c88ed4\"/>\n" +
                        "            <identifier>\n" +
                        "               <type>\n" +
                        "                  <coding>\n" +
                        "                     <system value=\"http://hssk.kcb.vn/identifier\"/>\n" +
                        "                     <code value=\"0100003271R\"/>\n" +
                        "                  </coding>\n" +
                        "               </type>\n" +
                        "            </identifier>\n" +
                        "            <name>\n" +
                        "               <text value=\"Nguyễn Huệ\"/>\n" +
                        "            </name>\n" +
                        "            <gender value=\"female\"/>\n" +
                        "            <birthDate value=\"1997-03-02\"/>\n" +
                        "            <address>\n" +
                        "               <text value=\"Thị trấn Vân Đình, Huyện Ứng Hòa, Thành phố Hà Nội\"/>\n" +
                        "            </address>\n" +
                        "         </Patient>\n" +
                        "      </resource>\n" +
                        "   </entry>\n" +
                        "   <entry>\n" +
                        "      <fullUrl value=\"urn:uuid:ed8fb970-59fb-4ab3-ab1d-ebecad742a06\"/>\n" +
                        "      <resource>\n" +
                        "         <AllergyIntolerance>\n" +
                        "            <id value=\"ed8fb970-59fb-4ab3-ab1d-ebecad742a06\"/>\n" +
                        "            <code>\n" +
                        "               <coding>\n" +
                        "                  <system value=\"http://dmdc.kcb.vn/#!/dmdcdiung\"/>\n" +
                        "                  <display value=\"Là thuốc chống viêm không steroid được sử dụng để điều trị đau khớp và đau cơ. Nó có sẵn cho các ứng dụng chuyên đề dưới dạng kem, gel hoặc dạng xịt. Etofenamate độc hại nặng nếu nuốt phải; nó cũng rất độc đối với đời sống thủy sinh, với tác dụng lâu dài\"/>\n" +
                        "               </coding>\n" +
                        "            </code>\n" +
                        "            <reaction>\n" +
                        "               <description value=\"Biểu hiện của dị ứng 1\"/>\n" +
                        "            </reaction>\n" +
                        "            <onsetString value=\"Thời gian phát hiện 1\"/>\n" +
                        "            <note>\n" +
                        "               <text value=\"Ghi chú 1\"/>\n" +
                        "            </note>\n" +
                        "         </AllergyIntolerance>\n" +
                        "      </resource>\n" +
                        "   </entry>\n" +
                        "   <entry>\n" +
                        "      <fullUrl value=\"urn:uuid:22ec162d-c8fe-443f-af68-cdf7ec4b5c81\"/>\n" +
                        "      <resource>\n" +
                        "         <AllergyIntolerance>\n" +
                        "            <id value=\"22ec162d-c8fe-443f-af68-cdf7ec4b5c81\"/>\n" +
                        "            <code>\n" +
                        "               <coding>\n" +
                        "                  <system value=\"http://dmdc.kcb.vn/#!/dmdcdiung\"/>\n" +
                        "                  <display value=\"Tên dị ứng khác abc\"/>\n" +
                        "               </coding>\n" +
                        "            </code>\n" +
                        "            <reaction>\n" +
                        "               <description value=\"Biểu hiện của dị ứng này\"/>\n" +
                        "            </reaction>\n" +
                        "            <onsetString value=\"12h\"/>\n" +
                        "            <note>\n" +
                        "               <text value=\"note dị ứng này\"/>\n" +
                        "            </note>\n" +
                        "         </AllergyIntolerance>\n" +
                        "      </resource>\n" +
                        "   </entry>\n" +
                        "   <entry>\n" +
                        "      <fullUrl value=\"urn:uuid:94daaf4f-66d9-4bc9-afe1-ec096334e36f\"/>\n" +
                        "      <resource>\n" +
                        "         <AllergyIntolerance>\n" +
                        "            <id value=\"94daaf4f-66d9-4bc9-afe1-ec096334e36f\"/>\n" +
                        "            <code>\n" +
                        "               <coding>\n" +
                        "                  <system value=\"http://dmdc.kcb.vn/#!/dmdcdiung\"/>\n" +
                        "                  <display value=\"Là một hợp chất được sử dụng trong quá trình lưu hóa lưu huỳnh của cao su\"/>\n" +
                        "               </coding>\n" +
                        "            </code>\n" +
                        "            <reaction>\n" +
                        "               <description value=\"Biểu hiện insert có dữ liệu 1'\"/>\n" +
                        "            </reaction>\n" +
                        "            <onsetString value=\"Thời gian 5h\"/>\n" +
                        "            <note>\n" +
                        "               <text value=\"Noyte\"/>\n" +
                        "            </note>\n" +
                        "         </AllergyIntolerance>\n" +
                        "      </resource>\n" +
                        "   </entry>\n" +
                        "   <entry>\n" +
                        "      <fullUrl value=\"urn:uuid:20651c87-12b6-4d29-a4de-57f55c6b459a\"/>\n" +
                        "      <resource>\n" +
                        "         <AllergyIntolerance>\n" +
                        "            <id value=\"20651c87-12b6-4d29-a4de-57f55c6b459a\"/>\n" +
                        "            <code>\n" +
                        "               <coding>\n" +
                        "                  <system value=\"http://dmdc.kcb.vn/#!/dmdcdiung\"/>\n" +
                        "                  <display value=\"Thuốc diệt nấm\"/>\n" +
                        "               </coding>\n" +
                        "            </code>\n" +
                        "            <reaction>\n" +
                        "               <description value=\"Biểu hiện insert có dữ liệu 1'\"/>\n" +
                        "            </reaction>\n" +
                        "         </AllergyIntolerance>\n" +
                        "      </resource>\n" +
                        "   </entry>\n" +
                        "</Bundle>\n";
            }
        });
        MockitoAnnotations.initMocks(this);

        String PID ="0420540030";
        Integer patientId = 1;
        String token = "token";
        String phoneNumber = "0909090909";
        RequestSyncHsskDTO dto = new RequestSyncHsskDTO(1L, 2L, PID, token, patientId,phoneNumber);
        //call service
        service.getMedicalAllergyInfo(dto);
    }

    @Test
    void getPresurgeryInfo() throws Exception {
        service = Mockito.spy(new HSSKService(){
            @Override
            public String requestDataHSSK(String url, String token) throws IOException {
                return "<Bundle>\n" +
                        "   <entry>\n" +
                        "      <fullUrl value=\"urn:uuid:1b4be6ea-ffb6-4c4f-a3e3-c6fe4a8ee6bb\"/>\n" +
                        "      <resource>\n" +
                        "         <Patient>\n" +
                        "            <id value=\"1b4be6ea-ffb6-4c4f-a3e3-c6fe4a8ee6bb\"/>\n" +
                        "            <identifier>\n" +
                        "               <type>\n" +
                        "                  <coding>\n" +
                        "                     <system value=\"http://hssk.kcb.vn/identifier\"/>\n" +
                        "                     <code value=\"0100003270E\"/>\n" +
                        "                  </coding>\n" +
                        "               </type>\n" +
                        "            </identifier>\n" +
                        "            <name>\n" +
                        "               <text value=\"Nguyễn Văn A\"/>\n" +
                        "            </name>\n" +
                        "            <gender value=\"female\"/>\n" +
                        "            <birthDate value=\"2000-04-02\"/>\n" +
                        "            <address>\n" +
                        "               <text value=\"Thị trấn Bắc Hà, Huyện Bắc Hà, Tỉnh Lào Cai\"/>\n" +
                        "            </address>\n" +
                        "         </Patient>\n" +
                        "      </resource>\n" +
                        "   </entry>\n" +
                        "   <entry>\n" +
                        "      <fullUrl value=\"urn:uuid:93b03cda-a727-48ca-bf0c-c635d3185f5b\"/>\n" +
                        "      <resource>\n" +
                        "         <Procedure>\n" +
                        "            <id value=\"93b03cda-a727-48ca-bf0c-c635d3185f5b\"/>\n" +
                        "            <bodySite>\n" +
                        "               <coding>\n" +
                        "                  <display value=\"pt\"/>\n" +
                        "               </coding>\n" +
                        "            </bodySite>\n" +
                        "            <outcome>\n" +
                        "               <text value=\"2323\"/>\n" +
                        "            </outcome>\n" +
                        "            <performedString value=\"2021\"/>\n" +
                        "            <complication>\n" +
                        "               <coding>\n" +
                        "                  <system value=\"http://snomed.info/sct\"/>\n" +
                        "                  <display value=\"2443\"/>\n" +
                        "               </coding>\n" +
                        "            </complication>\n" +
                        "            <performer>\n" +
                        "               <onBehalfOf>\n" +
                        "                  <reference value=\"Organization/9435ad28-8f00-4193-b71c-7439bb5075f8\"/>\n" +
                        "               </onBehalfOf>\n" +
                        "            </performer>\n" +
                        "         </Procedure>\n" +
                        "      </resource>\n" +
                        "   </entry>\n" +
                        "   <entry>\n" +
                        "      <fullUrl value=\"urn:uuid:9435ad28-8f00-4193-b71c-7439bb5075f8\"/>\n" +
                        "      <resource>\n" +
                        "         <Organization>\n" +
                        "            <id value=\"9435ad28-8f00-4193-b71c-7439bb5075f8\"/>\n" +
                        "            <name value=\"10022\"/>\n" +
                        "         </Organization>\n" +
                        "      </resource>\n" +
                        "   </entry>\n" +
                        "</Bundle>\n";
            }
        });
        MockitoAnnotations.initMocks(this);
        String PID ="0420540030";
        Integer patientId = 1;
        String token = "token";
        String phoneNumber = "0909090909";
        RequestSyncHsskDTO dto = new RequestSyncHsskDTO(1L, 2L, PID, token, patientId,phoneNumber);
        //call service
        service.getPresurgeryInfo(dto);
    }

    @Test
    void getImmunizationInfo() throws Exception {
        service = Mockito.spy(new HSSKService(){
            @Override
            public String requestDataHSSK(String url, String token) throws IOException {
                return "<Bundle>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:597e8273-30c2-413b-bc12-70e91772dcc9\"/>\n" +
                        "        <resource>\n" +
                        "            <Patient>\n" +
                        "                <id value=\"597e8273-30c2-413b-bc12-70e91772dcc9\"/>\n" +
                        "                <identifier>\n" +
                        "                    <type>\n" +
                        "                        <coding>\n" +
                        "                            <system value=\"http://hssk.kcb.vn/identifier\"/>\n" +
                        "                            <code value=\"0100003288R\"/>\n" +
                        "                        </coding>\n" +
                        "                    </type>\n" +
                        "                </identifier>\n" +
                        "                <name>\n" +
                        "                    <text value=\"Nguyễn Giang\"/>\n" +
                        "                </name>\n" +
                        "                <gender value=\"female\"/>\n" +
                        "                <birthDate value=\"1998-04-25\"/>\n" +
                        "                <address>\n" +
                        "                    <text value=\"Thị trấn Bắc Hà, Huyện Bắc Hà, Tỉnh Lào Cai\"/>\n" +
                        "                </address>\n" +
                        "            </Patient>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:41d84076-0b4f-46a1-8bf8-02b5abcbfbf3\"/>\n" +
                        "        <resource>\n" +
                        "            <Composition>\n" +
                        "                <id value=\"41d84076-0b4f-46a1-8bf8-02b5abcbfbf3\"/>\n" +
                        "                <section>\n" +
                        "                    <title value=\"Thông tin tiền sử tiêm chủng\"/>\n" +
                        "                    <section>\n" +
                        "                        <title value=\"Lịch hẹn khám\"/>\n" +
                        "                        <entry>\n" +
                        "                            <reference value=\"Immunization/ba2cd3fc-a9cc-44ba-97b4-c6c024c4b43b\"/>\n" +
                        "                        </entry>\n" +
                        "                        <entry>\n" +
                        "                            <reference value=\"Appointment/50fd3094-8ef4-4b37-83cc-e27797849b90\"/>\n" +
                        "                        </entry>\n" +
                        "                    </section>\n" +
                        "                    <section>\n" +
                        "                        <title value=\"Tiền sử tiêm chủng - Phản ứng sau tiêm\"/>\n" +
                        "                        <code>\n" +
                        "                            <text value=\"TC_Phan_ung_sau_tiem\"/>\n" +
                        "                        </code>\n" +
                        "                        <entry>\n" +
                        "                            <reference value=\"Observation/d2fbf46e-f9df-4403-a3fa-4995ed8df878\"/>\n" +
                        "                        </entry>\n" +
                        "                    </section>\n" +
                        "                    <section>\n" +
                        "                        <title value=\"Tiền sử tiêm chủng - Tình trạng sau tiêm\"/>\n" +
                        "                        <code>\n" +
                        "                            <text value=\"TC_Tinh_trang_sau_tiem\"/>\n" +
                        "                        </code>\n" +
                        "                        <entry>\n" +
                        "                            <reference value=\"Observation/51b616d6-2925-49d3-b26a-3b81dd0843f2\"/>\n" +
                        "                        </entry>\n" +
                        "                    </section>\n" +
                        "                    <section>\n" +
                        "                        <title value=\"Tiền sử tiêm chủng - Thông tin tử vong\"/>\n" +
                        "                        <code>\n" +
                        "                            <text value=\"TC_TT_tu_vong\"/>\n" +
                        "                        </code>\n" +
                        "                        <entry>\n" +
                        "                            <reference value=\"Observation/dcf2387a-15f5-44fe-88bb-57c60d79d3ef\"/>\n" +
                        "                        </entry>\n" +
                        "                    </section>\n" +
                        "                    <section>\n" +
                        "                        <title value=\"Tiền sử tiêm chủng - Thông tin xử trí sau tiêm\"/>\n" +
                        "                        <code>\n" +
                        "                            <text value=\"TC_TT_xu_tri_sau_tiem\"/>\n" +
                        "                        </code>\n" +
                        "                        <entry>\n" +
                        "                            <reference value=\"Observation/bc924d12-4b92-4dfa-8d79-6dd0fde8c3f4\"/>\n" +
                        "                        </entry>\n" +
                        "                    </section>\n" +
                        "                </section>\n" +
                        "            </Composition>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:ba2cd3fc-a9cc-44ba-97b4-c6c024c4b43b\"/>\n" +
                        "        <resource>\n" +
                        "            <Immunization>\n" +
                        "                <id value=\"ba2cd3fc-a9cc-44ba-97b4-c6c024c4b43b\"/>\n" +
                        "                <vaccineCode>\n" +
                        "                    <coding>\n" +
                        "                        <system value=\"http://dmdc.kcb.vn/dmdc/vacxinkhangnguyen\"/>\n" +
                        "                        <display value=\"Abhayrab 0.1ml\"/>\n" +
                        "                    </coding>\n" +
                        "                </vaccineCode>\n" +
                        "                <status value=\"entered-in-error\"/>\n" +
                        "                <protocolApplied>\n" +
                        "                    <doseNumberPositiveInt value=\"4\"/>\n" +
                        "                </protocolApplied>\n" +
                        "                <occurrenceDateTime value=\"2018-01-01\"/>\n" +
                        "                <extension url=\"https://www.moh.gov.vn/extension/TiemChung/CO_SEO\">\n" +
                        "                    <valueString value=\"Không\"/>\n" +
                        "                </extension>\n" +
                        "                <extension url=\"https://dmdc.kcb.vn/extension/TiemChung/LoaiVaccine\">\n" +
                        "                    <valueString value=\"Tiêm chủng MR\"/>\n" +
                        "                </extension>\n" +
                        "                <extension url=\"https://www.moh.gov.vn/extension/TiemChung/CO_LICH_TRUOC_24H\">\n" +
                        "                    <valueString value=\"Không\"/>\n" +
                        "                </extension>\n" +
                        "                <route>\n" +
                        "                    <coding>\n" +
                        "                        <system value=\"http://hl7.org/fhir/ValueSet/immunization-route\"/>\n" +
                        "                        <display value=\"Tiêm trong da\"/>\n" +
                        "                    </coding>\n" +
                        "                    <text value=\"Tiêm trong da\"/>\n" +
                        "                </route>\n" +
                        "                <site>\n" +
                        "                    <text value=\"Cánh tay\"/>\n" +
                        "                </site>\n" +
                        "                <extension url=\"http://dmdc.kcb.vn/extension/hinhthuctiem\">\n" +
                        "                    <valueString value=\"Thường xuyên\"/>\n" +
                        "                </extension>\n" +
                        "                <lotNumber value=\"Lô 01\"/>\n" +
                        "                <protocolApplied>\n" +
                        "                    <targetDisease>\n" +
                        "                        <extension url=\"http://dmdc.kcb.vn/extension/TiemChung/VGBBss24h\">\n" +
                        "                            <valueString value=\"Không\"/>\n" +
                        "                        </extension>\n" +
                        "                        <code>\n" +
                        "                            <system value=\"http://dmdc.kcb.vn/dmdc/khangnguyen\"/>\n" +
                        "                            <display value=\"Dại, Cúm\"/>\n" +
                        "                        </code>\n" +
                        "                    </targetDisease>\n" +
                        "                </protocolApplied>\n" +
                        "                <extension url=\"http://dmdc.kcb.vn/extension/ketquatonghop\">\n" +
                        "                    <valueString value=\" Sốt nhỏ hơn 39 độ, Sưng đau tại chỗ tiêm \"/>\n" +
                        "                </extension>\n" +
                        "                <reaction>\n" +
                        "                    <detail>\n" +
                        "                        <reference value=\"Observation/37fd16ab-dd53-4b67-96d0-7ad3980f35ab\"/>\n" +
                        "                    </detail>\n" +
                        "                </reaction>\n" +
                        "                <encounter>\n" +
                        "                    <reference value=\"Encounter/be3562ae-dad5-4737-ab53-92a406a131df\"/>\n" +
                        "                </encounter>\n" +
                        "                <performer>\n" +
                        "                    <actor>\n" +
                        "                        <reference value=\"Organization/0fa5df27-724a-4706-be57-77c969d38b27\"/>\n" +
                        "                    </actor>\n" +
                        "                </performer>\n" +
                        "                <manufacturer>\n" +
                        "                    <reference value=\"Organization/222b9d48-9bf3-457f-b3b0-a986cceae2dd\"/>\n" +
                        "                </manufacturer>\n" +
                        "                <reasonReference>\n" +
                        "                    <reference value=\"Condition/5e64056c-7be7-4550-9ae4-dc7d35e73233\"/>\n" +
                        "                </reasonReference>\n" +
                        "            </Immunization>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:d2fbf46e-f9df-4403-a3fa-4995ed8df878\"/>\n" +
                        "        <resource>\n" +
                        "            <Observation>\n" +
                        "                <id value=\"d2fbf46e-f9df-4403-a3fa-4995ed8df878\"/>\n" +
                        "            </Observation>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:bc924d12-4b92-4dfa-8d79-6dd0fde8c3f4\"/>\n" +
                        "        <resource>\n" +
                        "            <Observation>\n" +
                        "                <id value=\"bc924d12-4b92-4dfa-8d79-6dd0fde8c3f4\"/>\n" +
                        "                <extension url=\"http://dmdc.kcb.vn/extension/TiemChung/NOI_XU_TRI\">\n" +
                        "                    <valueString value=\"Tại nhà\"/>\n" +
                        "                </extension>\n" +
                        "                <valueString value=\"Mô tả cách thức xử trí \"/>\n" +
                        "                <code>\n" +
                        "                    <coding>\n" +
                        "                        <system value=\"http://dmdc.kcb.vn/CodeSystem/tiemchung/xutrisautiem\"/>\n" +
                        "                        <code value=\"Có\"/>\n" +
                        "                    </coding>\n" +
                        "                </code>\n" +
                        "            </Observation>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:51b616d6-2925-49d3-b26a-3b81dd0843f2\"/>\n" +
                        "        <resource>\n" +
                        "            <Observation>\n" +
                        "                <id value=\"51b616d6-2925-49d3-b26a-3b81dd0843f2\"/>\n" +
                        "                <code>\n" +
                        "                    <coding>\n" +
                        "                        <system value=\"http://dmdc.kcb.vn/CodeSystem/tiemchung/tinhtranghientai\"/>\n" +
                        "                        <code value=\"Khỏi\"/>\n" +
                        "                    </coding>\n" +
                        "                </code>\n" +
                        "            </Observation>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:dcf2387a-15f5-44fe-88bb-57c60d79d3ef\"/>\n" +
                        "        <resource>\n" +
                        "            <Observation>\n" +
                        "                <id value=\"dcf2387a-15f5-44fe-88bb-57c60d79d3ef\"/>\n" +
                        "            </Observation>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:5e64056c-7be7-4550-9ae4-dc7d35e73233\"/>\n" +
                        "        <resource>\n" +
                        "            <Condition>\n" +
                        "                <id value=\"5e64056c-7be7-4550-9ae4-dc7d35e73233\"/>\n" +
                        "            </Condition>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:222b9d48-9bf3-457f-b3b0-a986cceae2dd\"/>\n" +
                        "        <resource>\n" +
                        "            <Organization>\n" +
                        "                <id value=\"222b9d48-9bf3-457f-b3b0-a986cceae2dd\"/>\n" +
                        "            </Organization>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:37fd16ab-dd53-4b67-96d0-7ad3980f35ab\"/>\n" +
                        "        <resource>\n" +
                        "            <Observation>\n" +
                        "                <id value=\"37fd16ab-dd53-4b67-96d0-7ad3980f35ab\"/>\n" +
                        "                <valueString value=\"Phản ứng thông thường\"/>\n" +
                        "            </Observation>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:be3562ae-dad5-4737-ab53-92a406a131df\"/>\n" +
                        "        <resource>\n" +
                        "            <Encounter>\n" +
                        "                <id value=\"be3562ae-dad5-4737-ab53-92a406a131df\"/>\n" +
                        "                <appointment>\n" +
                        "                    <reference value=\"Appointment/50fd3094-8ef4-4b37-83cc-e27797849b90\"/>\n" +
                        "                </appointment>\n" +
                        "            </Encounter>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:50fd3094-8ef4-4b37-83cc-e27797849b90\"/>\n" +
                        "        <resource>\n" +
                        "            <Appointment>\n" +
                        "                <id value=\"50fd3094-8ef4-4b37-83cc-e27797849b90\"/>\n" +
                        "                <start value=\"2020-01-01\"/>\n" +
                        "            </Appointment>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:0fa5df27-724a-4706-be57-77c969d38b27\"/>\n" +
                        "        <resource>\n" +
                        "            <Organization>\n" +
                        "                <id value=\"0fa5df27-724a-4706-be57-77c969d38b27\"/>\n" +
                        "                <identifier>\n" +
                        "                    <system value=\"http://dmdc.kcb.vn/dmdc/cosoyte\"/>\n" +
                        "                    <value value=\"01014\"/>\n" +
                        "                </identifier>\n" +
                        "                <name value=\"Bệnh viện trung ương Quân đội 108\"/>\n" +
                        "            </Organization>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "</Bundle>";
            }
        });
        MockitoAnnotations.initMocks(this);
        PatientDTO patientInfo = new PatientDTO();
        String PID ="0420540030";
        Integer patientId = 1;
        String token = "token";
        String phoneNumber = "0909090909";
        RequestSyncHsskDTO dto = new RequestSyncHsskDTO(1L, 2L, PID, token, patientId,phoneNumber);
        //call service
        service.getImmunizationInfo(dto, patientInfo);
    }

    @Test
    void getPatientHistoryInfo() throws Exception {
        service = Mockito.spy(new HSSKService(){
            @Override
            public String requestDataHSSK(String url, String token) throws IOException {
                return "<Bundle>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:e8beaad2-1686-4751-b3e3-5aeddfd983b3\"/>\n" +
                        "        <resource>\n" +
                        "            <Composition>\n" +
                        "                <id value=\"e8beaad2-1686-4751-b3e3-5aeddfd983b3\"/>\n" +
                        "                <section>\n" +
                        "                    <title value=\"Thông tin đợt khám chữa bệnh\"/>\n" +
                        "                </section>\n" +
                        "            </Composition>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:3fbea662-51f1-4437-abdc-175ff3f82d58\"/>\n" +
                        "        <resource>\n" +
                        "            <Patient>\n" +
                        "                <id value=\"3fbea662-51f1-4437-abdc-175ff3f82d58\"/>\n" +
                        "                <identifier>\n" +
                        "                    <type>\n" +
                        "                        <coding>\n" +
                        "                            <system value=\"http://hssk.kcb.vn/identifier\"/>\n" +
                        "                            <code value=\"0100003288R\"/>\n" +
                        "                        </coding>\n" +
                        "                    </type>\n" +
                        "                </identifier>\n" +
                        "                <name>\n" +
                        "                    <text value=\"Nguyễn Giang\"/>\n" +
                        "                </name>\n" +
                        "                <gender value=\"female\"/>\n" +
                        "                <birthDate value=\"1998-04-25\"/>\n" +
                        "                <address>\n" +
                        "                    <text value=\"Thị trấn Bắc Hà, Huyện Bắc Hà, Tỉnh Lào Cai\"/>\n" +
                        "                </address>\n" +
                        "            </Patient>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:69310148-6bff-4f67-9196-2184d0975da7\"/>\n" +
                        "        <resource>\n" +
                        "            <CarePlan>\n" +
                        "                <id value=\"69310148-6bff-4f67-9196-2184d0975da7\"/>\n" +
                        "                <encounter>\n" +
                        "                    <reference value=\"Encounter/7444b5f6-3b5a-41ee-87e9-a5b3462018e9\"/>\n" +
                        "                </encounter>\n" +
                        "            </CarePlan>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:7444b5f6-3b5a-41ee-87e9-a5b3462018e9\"/>\n" +
                        "        <resource>\n" +
                        "            <Encounter>\n" +
                        "                <id value=\"7444b5f6-3b5a-41ee-87e9-a5b3462018e9\"/>\n" +
                        "                <period>\n" +
                        "                    <start value=\"2021-04-19\"/>\n" +
                        "                    <end value=\"2021-04-19\"/>\n" +
                        "                </period>\n" +
                        "                <hospitalization>\n" +
                        "                    <origin>\n" +
                        "                        <reference value=\"Organization/b3a4f582-3ed1-4d29-9f6e-b33e5aaa8359\"/>\n" +
                        "                    </origin>\n" +
                        "                </hospitalization>\n" +
                        "                <extension url=\"http://dmdc.kcb.vn/dmloaihinhkcb\">\n" +
                        "                    <valueString value=\"Khám bệnh\"/>\n" +
                        "                </extension>\n" +
                        "                <participant>\n" +
                        "                    <individual>\n" +
                        "                        <reference value=\"Practitioner/9d96bc85-4014-4108-a3fc-d87fef3ea3bf\"/>\n" +
                        "                    </individual>\n" +
                        "                </participant>\n" +
                        "                <appointment>\n" +
                        "                    <reference value=\"Appointment/f2807f93-5495-47d7-b6d8-2a72017efb76\"/>\n" +
                        "                </appointment>\n" +
                        "                <diagnosis>\n" +
                        "                    <condition>\n" +
                        "                        <reference value=\"Condition/0e557df0-c77d-4fac-ae20-8d1ef33f84bb\"/>\n" +
                        "                    </condition>\n" +
                        "                </diagnosis>\n" +
                        "                <reasonReference>\n" +
                        "                    <reference value=\"Condition/33062201-20ff-4fd5-ac68-2fe8000bc7ee\"/>\n" +
                        "                </reasonReference>\n" +
                        "                <reasonReference>\n" +
                        "                    <reference value=\"Observation/236b22a3-821d-4bf6-894b-fec29ed44cb6\"/>\n" +
                        "                </reasonReference>\n" +
                        "            </Encounter>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:b3a4f582-3ed1-4d29-9f6e-b33e5aaa8359\"/>\n" +
                        "        <resource>\n" +
                        "            <Organization>\n" +
                        "                <id value=\"b3a4f582-3ed1-4d29-9f6e-b33e5aaa8359\"/>\n" +
                        "                <identifier>\n" +
                        "                    <system value=\"http://dmdc.kcb.vn/#!/dmdccosoyte\"/>\n" +
                        "                    <value value=\"10022\"/>\n" +
                        "                </identifier>\n" +
                        "            </Organization>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:9d96bc85-4014-4108-a3fc-d87fef3ea3bf\"/>\n" +
                        "        <resource>\n" +
                        "            <Practitioner>\n" +
                        "                <id value=\"9d96bc85-4014-4108-a3fc-d87fef3ea3bf\"/>\n" +
                        "                <name>\n" +
                        "                    <text value=\"Demo hồ sơ sức khỏe\"/>\n" +
                        "                </name>\n" +
                        "            </Practitioner>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:f2807f93-5495-47d7-b6d8-2a72017efb76\"/>\n" +
                        "        <resource>\n" +
                        "            <Appointment>\n" +
                        "                <id value=\"f2807f93-5495-47d7-b6d8-2a72017efb76\"/>\n" +
                        "            </Appointment>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:0e557df0-c77d-4fac-ae20-8d1ef33f84bb\"/>\n" +
                        "        <resource>\n" +
                        "            <Condition>\n" +
                        "                <id value=\"0e557df0-c77d-4fac-ae20-8d1ef33f84bb\"/>\n" +
                        "                <stage>\n" +
                        "                    <summary>\n" +
                        "                        <coding>\n" +
                        "                            <system value=\"http://icd.kcb.vn/ICD/\"/>\n" +
                        "                            <display value=\"A00.0 - Bệnh tả do Vibrio cholerae 01, typ sinh học cholerae\"/>\n" +
                        "                        </coding>\n" +
                        "                    </summary>\n" +
                        "                </stage>\n" +
                        "            </Condition>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:33062201-20ff-4fd5-ac68-2fe8000bc7ee\"/>\n" +
                        "        <resource>\n" +
                        "            <Condition>\n" +
                        "                <id value=\"33062201-20ff-4fd5-ac68-2fe8000bc7ee\"/>\n" +
                        "            </Condition>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:3ee8d2eb-d228-4fdd-b5d0-3a1d4f105415\"/>\n" +
                        "        <resource>\n" +
                        "            <Condition>\n" +
                        "                <id value=\"3ee8d2eb-d228-4fdd-b5d0-3a1d4f105415\"/>\n" +
                        "                <code>\n" +
                        "                    <coding>\n" +
                        "                        <system value=\"http://icd.kcb.vn/ICD/\"/>\n" +
                        "                        <code value=\"A00.0\"/>\n" +
                        "                        <display value=\"Bệnh tả do Vibrio cholerae 01, typ sinh học cholerae\"/>\n" +
                        "                    </coding>\n" +
                        "                </code>\n" +
                        "                <onsetDateTime value=\"2021-04-19\"/>\n" +
                        "                <encounter>\n" +
                        "                    <reference value=\"Encounter/7444b5f6-3b5a-41ee-87e9-a5b3462018e9\"/>\n" +
                        "                </encounter>\n" +
                        "            </Condition>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:b82f4e04-2172-449a-9e3f-196f8d0ef2c7\"/>\n" +
                        "        <resource>\n" +
                        "            <CarePlan>\n" +
                        "                <id value=\"b82f4e04-2172-449a-9e3f-196f8d0ef2c7\"/>\n" +
                        "                <encounter>\n" +
                        "                    <reference value=\"Encounter/de6cf7b7-5cdc-4758-bfd2-dcedae97a24d\"/>\n" +
                        "                </encounter>\n" +
                        "                <activity>\n" +
                        "                    <detail>\n" +
                        "                        <productReference>\n" +
                        "                            <reference value=\"Medication/85310908-96a3-4a14-a4cf-a802ebf5faba\"/>\n" +
                        "                        </productReference>\n" +
                        "                    </detail>\n" +
                        "                </activity>\n" +
                        "            </CarePlan>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:de6cf7b7-5cdc-4758-bfd2-dcedae97a24d\"/>\n" +
                        "        <resource>\n" +
                        "            <Encounter>\n" +
                        "                <id value=\"de6cf7b7-5cdc-4758-bfd2-dcedae97a24d\"/>\n" +
                        "                <period>\n" +
                        "                    <start value=\"2020-01-01\"/>\n" +
                        "                    <end value=\"2020-01-01\"/>\n" +
                        "                </period>\n" +
                        "                <hospitalization>\n" +
                        "                    <origin>\n" +
                        "                        <reference value=\"Organization/34cec9cf-0235-44cf-b39f-2438a23bac2a\"/>\n" +
                        "                    </origin>\n" +
                        "                </hospitalization>\n" +
                        "                <extension url=\"http://dmdc.kcb.vn/dmloaihinhkcb\">\n" +
                        "                    <valueString value=\"Khám bệnh\"/>\n" +
                        "                </extension>\n" +
                        "                <participant>\n" +
                        "                    <individual>\n" +
                        "                        <reference value=\"Practitioner/631b95b1-229a-41a2-87e7-cb74a0c9fff3\"/>\n" +
                        "                    </individual>\n" +
                        "                </participant>\n" +
                        "                <appointment>\n" +
                        "                    <reference value=\"Appointment/809c9660-db46-475a-998f-4e0f665438f9\"/>\n" +
                        "                </appointment>\n" +
                        "                <diagnosis>\n" +
                        "                    <condition>\n" +
                        "                        <reference value=\"Condition/d1e5d6d5-db60-4c91-bbb0-99cb7320e95a\"/>\n" +
                        "                    </condition>\n" +
                        "                </diagnosis>\n" +
                        "                <reasonReference>\n" +
                        "                    <reference value=\"Condition/14633073-db28-4363-80ba-7462c04a2730\"/>\n" +
                        "                </reasonReference>\n" +
                        "                <reasonReference>\n" +
                        "                    <reference value=\"Observation/732df1ef-16a4-4888-9f66-7647c82bcae8\"/>\n" +
                        "                </reasonReference>\n" +
                        "                <basedOn>\n" +
                        "                    <reference value=\"ServiceRequest/d073c60b-6c22-44af-88ad-650de8f42ce9\"/>\n" +
                        "                </basedOn>\n" +
                        "            </Encounter>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:34cec9cf-0235-44cf-b39f-2438a23bac2a\"/>\n" +
                        "        <resource>\n" +
                        "            <Organization>\n" +
                        "                <id value=\"34cec9cf-0235-44cf-b39f-2438a23bac2a\"/>\n" +
                        "                <identifier>\n" +
                        "                    <system value=\"http://dmdc.kcb.vn/#!/dmdccosoyte\"/>\n" +
                        "                    <value value=\"92000\"/>\n" +
                        "                </identifier>\n" +
                        "            </Organization>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:631b95b1-229a-41a2-87e7-cb74a0c9fff3\"/>\n" +
                        "        <resource>\n" +
                        "            <Practitioner>\n" +
                        "                <id value=\"631b95b1-229a-41a2-87e7-cb74a0c9fff3\"/>\n" +
                        "                <name>\n" +
                        "                    <text value=\"Khoa sơ sinh\"/>\n" +
                        "                </name>\n" +
                        "            </Practitioner>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:809c9660-db46-475a-998f-4e0f665438f9\"/>\n" +
                        "        <resource>\n" +
                        "            <Appointment>\n" +
                        "                <id value=\"809c9660-db46-475a-998f-4e0f665438f9\"/>\n" +
                        "                <requestedPeriod>\n" +
                        "                    <start value=\"2020-02-01\"/>\n" +
                        "                </requestedPeriod>\n" +
                        "                                            me\n" +
                        "            </Appointment>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:d1e5d6d5-db60-4c91-bbb0-99cb7320e95a\"/>\n" +
                        "        <resource>\n" +
                        "            <Condition>\n" +
                        "                <id value=\"d1e5d6d5-db60-4c91-bbb0-99cb7320e95a\"/>\n" +
                        "                <stage>\n" +
                        "                    <summary>\n" +
                        "                        <coding>\n" +
                        "                            <system value=\"http://icd.kcb.vn/ICD/\"/>\n" +
                        "                            <display value=\"A02.2 - Nhiễm trùng Salmonella khu trú, A37 - Bệnh ho gà, A02.2 - Nhiễm trùng Salmonella khu trú (Mặc định là LOAI_CHAN_DOAN = 4)\"/>\n" +
                        "                        </coding>\n" +
                        "                    </summary>\n" +
                        "                </stage>\n" +
                        "            </Condition>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:14633073-db28-4363-80ba-7462c04a2730\"/>\n" +
                        "        <resource>\n" +
                        "            <Condition>\n" +
                        "                <id value=\"14633073-db28-4363-80ba-7462c04a2730\"/>\n" +
                        "            </Condition>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:d073c60b-6c22-44af-88ad-650de8f42ce9\"/>\n" +
                        "        <resource>\n" +
                        "            <ServiceRequest>\n" +
                        "                <id value=\"d073c60b-6c22-44af-88ad-650de8f42ce9\"/>\n" +
                        "                <code>\n" +
                        "                    <coding>\n" +
                        "                        <system value=\"http://icd.kcb.vn/ICD/\"/>\n" +
                        "                        <code value=\"01\"/>\n" +
                        "                        <display value=\"xét nghiệm huyết học\"/>\n" +
                        "                    </coding>\n" +
                        "                </code>\n" +
                        "                <category>\n" +
                        "                    <coding>\n" +
                        "                        <system value=\"http://icd.kcb.vn/ICD/\"/>\n" +
                        "                        <code value=\"3\"/>\n" +
                        "                    </coding>\n" +
                        "                </category>\n" +
                        "                <quantityQuantity>\n" +
                        "                    <system value=\"http://icd.kcb.vn/ICD/\"/>\n" +
                        "                    <value value=\"1.00\"/>\n" +
                        "                    <unit value=\"Lần\"/>\n" +
                        "                </quantityQuantity>\n" +
                        "                <supportingInfo>\n" +
                        "                    <reference value=\"ClinicalImpression/80800967-3740-4139-a802-4b1548f05a17\"/>\n" +
                        "                </supportingInfo>\n" +
                        "                <reasonCode>\n" +
                        "                    <coding>\n" +
                        "                        <display value=\"Không có chỉ số bất thường\"/>\n" +
                        "                    </coding>\n" +
                        "                </reasonCode>\n" +
                        "                <occurrenceDateTime value=\"2020-04-10\"/>\n" +
                        "                <basedOn>\n" +
                        "                    <reference value=\"ServiceRequest/3c104363-fdf1-4891-9f92-074d971f7d7e\"/>\n" +
                        "                </basedOn>\n" +
                        "                <basedOn>\n" +
                        "                    <reference value=\"ServiceRequest/8345aad9-3a61-40f2-a13c-b60e2019c6bc\"/>\n" +
                        "                </basedOn>\n" +
                        "            </ServiceRequest>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:80800967-3740-4139-a802-4b1548f05a17\"/>\n" +
                        "        <resource>\n" +
                        "            <ClinicalImpression>\n" +
                        "                <id value=\"80800967-3740-4139-a802-4b1548f05a17\"/>\n" +
                        "                <summary value=\"Không mắc bệnh\"/>\n" +
                        "            </ClinicalImpression>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:3c104363-fdf1-4891-9f92-074d971f7d7e\"/>\n" +
                        "        <resource>\n" +
                        "            <ServiceRequest>\n" +
                        "                <id value=\"3c104363-fdf1-4891-9f92-074d971f7d7e\"/>\n" +
                        "                <code>\n" +
                        "                    <coding>\n" +
                        "                        <system value=\"http://icd.kcb.vn/ICD/\"/>\n" +
                        "                        <code value=\"01\"/>\n" +
                        "                        <display value=\"Định lượng Troponin\"/>\n" +
                        "                    </coding>\n" +
                        "                </code>\n" +
                        "                <quantityQuantity>\n" +
                        "                    <system value=\"http://icd.kcb.vn/ICD/\"/>\n" +
                        "                    <value value=\"0.012\"/>\n" +
                        "                    <unit value=\"Lần\"/>\n" +
                        "                </quantityQuantity>\n" +
                        "                <reasonReference>\n" +
                        "                    <reference value=\"Observation/1fe4b4c0-f0b5-45ab-b49c-ae8bbe0f7915\"/>\n" +
                        "                </reasonReference>\n" +
                        "            </ServiceRequest>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:1fe4b4c0-f0b5-45ab-b49c-ae8bbe0f7915\"/>\n" +
                        "        <resource>\n" +
                        "            <Observation>\n" +
                        "                <id value=\"1fe4b4c0-f0b5-45ab-b49c-ae8bbe0f7915\"/>\n" +
                        "                <valueRange>\n" +
                        "                    <low>\n" +
                        "                        <value value=\"0\"/>\n" +
                        "                    </low>\n" +
                        "                    <high>\n" +
                        "                        <value value=\"0.014\"/>\n" +
                        "                    </high>\n" +
                        "                </valueRange>\n" +
                        "            </Observation>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:8345aad9-3a61-40f2-a13c-b60e2019c6bc\"/>\n" +
                        "        <resource>\n" +
                        "            <ServiceRequest>\n" +
                        "                <id value=\"8345aad9-3a61-40f2-a13c-b60e2019c6bc\"/>\n" +
                        "                <code>\n" +
                        "                    <coding>\n" +
                        "                        <system value=\"http://icd.kcb.vn/ICD/\"/>\n" +
                        "                        <code value=\"H21\"/>\n" +
                        "                        <display value=\"Mono\"/>\n" +
                        "                    </coding>\n" +
                        "                </code>\n" +
                        "                <quantityQuantity>\n" +
                        "                    <system value=\"http://icd.kcb.vn/ICD/\"/>\n" +
                        "                    <value value=\"7.55\"/>\n" +
                        "                    <unit value=\"Lần\"/>\n" +
                        "                </quantityQuantity>\n" +
                        "                <reasonReference>\n" +
                        "                    <reference value=\"Observation/0f6eb826-0ebb-4bf9-a6c0-6c9d2a8c9c2d\"/>\n" +
                        "                </reasonReference>\n" +
                        "            </ServiceRequest>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:0f6eb826-0ebb-4bf9-a6c0-6c9d2a8c9c2d\"/>\n" +
                        "        <resource>\n" +
                        "            <Observation>\n" +
                        "                <id value=\"0f6eb826-0ebb-4bf9-a6c0-6c9d2a8c9c2d\"/>\n" +
                        "                <valueRange>\n" +
                        "                    <low>\n" +
                        "                        <value value=\"4.66\"/>\n" +
                        "                    </low>\n" +
                        "                    <high>\n" +
                        "                        <value value=\"11.95\"/>\n" +
                        "                    </high>\n" +
                        "                </valueRange>\n" +
                        "            </Observation>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:0bf92ec7-4236-43a4-9bf4-e521af168855\"/>\n" +
                        "        <resource>\n" +
                        "            <Condition>\n" +
                        "                <id value=\"0bf92ec7-4236-43a4-9bf4-e521af168855\"/>\n" +
                        "                <code>\n" +
                        "                    <coding>\n" +
                        "                        <system value=\"http://icd.kcb.vn/ICD/\"/>\n" +
                        "                        <code value=\"A02.2\"/>\n" +
                        "                        <display value=\"Nhiễm trùng Salmonella khu trú\"/>\n" +
                        "                    </coding>\n" +
                        "                </code>\n" +
                        "                <onsetDateTime value=\"2020-01-01\"/>\n" +
                        "                <encounter>\n" +
                        "                    <reference value=\"Encounter/de6cf7b7-5cdc-4758-bfd2-dcedae97a24d\"/>\n" +
                        "                </encounter>\n" +
                        "            </Condition>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:287038ff-c69c-441c-a7db-f9bf1215b11c\"/>\n" +
                        "        <resource>\n" +
                        "            <Condition>\n" +
                        "                <id value=\"287038ff-c69c-441c-a7db-f9bf1215b11c\"/>\n" +
                        "                <code>\n" +
                        "                    <coding>\n" +
                        "                        <system value=\"http://icd.kcb.vn/ICD/\"/>\n" +
                        "                        <code value=\"A37\"/>\n" +
                        "                        <display value=\"Bệnh ho gà\"/>\n" +
                        "                    </coding>\n" +
                        "                </code>\n" +
                        "                <onsetDateTime value=\"2020-01-01\"/>\n" +
                        "                <encounter>\n" +
                        "                    <reference value=\"Encounter/de6cf7b7-5cdc-4758-bfd2-dcedae97a24d\"/>\n" +
                        "                </encounter>\n" +
                        "            </Condition>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:0ac1f72c-5e21-445f-809c-e8f8ee9d0f62\"/>\n" +
                        "        <resource>\n" +
                        "            <Condition>\n" +
                        "                <id value=\"0ac1f72c-5e21-445f-809c-e8f8ee9d0f62\"/>\n" +
                        "                <code>\n" +
                        "                    <coding>\n" +
                        "                        <system value=\"http://icd.kcb.vn/ICD/\"/>\n" +
                        "                        <code value=\"A02.2\"/>\n" +
                        "                        <display value=\"Nhiễm trùng Salmonella khu trú (Mặc định là LOAI_CHAN_DOAN = 4)\"/>\n" +
                        "                    </coding>\n" +
                        "                </code>\n" +
                        "                <onsetDateTime value=\"2020-01-01\"/>\n" +
                        "                <encounter>\n" +
                        "                    <reference value=\"Encounter/de6cf7b7-5cdc-4758-bfd2-dcedae97a24d\"/>\n" +
                        "                </encounter>\n" +
                        "            </Condition>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:95cb9abc-5990-40c8-b774-f27c2a40d8d8\"/>\n" +
                        "        <resource>\n" +
                        "            <MedicationAdministration>\n" +
                        "                <id value=\"95cb9abc-5990-40c8-b774-f27c2a40d8d8\"/>\n" +
                        "                <dosage>\n" +
                        "                    <dose>\n" +
                        "                        <unit value=\"viên\"/>\n" +
                        "                    </dose>\n" +
                        "                    <route>\n" +
                        "                        <coding>\n" +
                        "                            <display value=\"Uống\"/>\n" +
                        "                        </coding>\n" +
                        "                    </route>\n" +
                        "                    <text value=\"Uống trước khi ăn cơm 30 phút\"/>\n" +
                        "                </dosage>\n" +
                        "                <effectiveDateTime value=\"2020-04-10\"/>\n" +
                        "                <medicationReference>\n" +
                        "                    <reference value=\"Medication/85310908-96a3-4a14-a4cf-a802ebf5faba\"/>\n" +
                        "                </medicationReference>\n" +
                        "            </MedicationAdministration>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:a3d4bc02-6751-424a-a0c8-eebfeef550e6\"/>\n" +
                        "        <resource>\n" +
                        "            <MedicationAdministration>\n" +
                        "                <id value=\"a3d4bc02-6751-424a-a0c8-eebfeef550e6\"/>\n" +
                        "                <category>\n" +
                        "                    <coding>\n" +
                        "                        <system value=\"http://dmdc.kcb.vn/CodeSystem/Thuoc/ThoiGIanDung\"/>\n" +
                        "                        <code value=\"1\"/>\n" +
                        "                    </coding>\n" +
                        "                </category>\n" +
                        "                <dosage>\n" +
                        "                    <rateQuantity>\n" +
                        "                        <value value=\"0\"/>\n" +
                        "                    </rateQuantity>\n" +
                        "                </dosage>\n" +
                        "                <medicationReference>\n" +
                        "                    <reference value=\"Medication/85310908-96a3-4a14-a4cf-a802ebf5faba\"/>\n" +
                        "                </medicationReference>\n" +
                        "            </MedicationAdministration>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:107c1fa9-bf97-487b-a24b-7f05c7cdb6e4\"/>\n" +
                        "        <resource>\n" +
                        "            <MedicationAdministration>\n" +
                        "                <id value=\"107c1fa9-bf97-487b-a24b-7f05c7cdb6e4\"/>\n" +
                        "                <category>\n" +
                        "                    <coding>\n" +
                        "                        <system value=\"http://dmdc.kcb.vn/CodeSystem/Thuoc/ThoiGIanDung\"/>\n" +
                        "                        <code value=\"2\"/>\n" +
                        "                    </coding>\n" +
                        "                </category>\n" +
                        "                <dosage>\n" +
                        "                    <rateQuantity>\n" +
                        "                        <value value=\"0\"/>\n" +
                        "                    </rateQuantity>\n" +
                        "                </dosage>\n" +
                        "                <medicationReference>\n" +
                        "                    <reference value=\"Medication/85310908-96a3-4a14-a4cf-a802ebf5faba\"/>\n" +
                        "                </medicationReference>\n" +
                        "            </MedicationAdministration>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:a90ec94c-6832-4710-a5ba-ce186a3c5e37\"/>\n" +
                        "        <resource>\n" +
                        "            <MedicationAdministration>\n" +
                        "                <id value=\"a90ec94c-6832-4710-a5ba-ce186a3c5e37\"/>\n" +
                        "                <category>\n" +
                        "                    <coding>\n" +
                        "                        <system value=\"http://dmdc.kcb.vn/CodeSystem/Thuoc/ThoiGIanDung\"/>\n" +
                        "                        <code value=\"3\"/>\n" +
                        "                    </coding>\n" +
                        "                </category>\n" +
                        "                <dosage>\n" +
                        "                    <rateQuantity>\n" +
                        "                        <value value=\"0\"/>\n" +
                        "                    </rateQuantity>\n" +
                        "                </dosage>\n" +
                        "                <medicationReference>\n" +
                        "                    <reference value=\"Medication/85310908-96a3-4a14-a4cf-a802ebf5faba\"/>\n" +
                        "                </medicationReference>\n" +
                        "            </MedicationAdministration>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:1a5fa025-3abc-4007-a573-a5a28eaef520\"/>\n" +
                        "        <resource>\n" +
                        "            <MedicationAdministration>\n" +
                        "                <id value=\"1a5fa025-3abc-4007-a573-a5a28eaef520\"/>\n" +
                        "                <category>\n" +
                        "                    <coding>\n" +
                        "                        <system value=\"http://dmdc.kcb.vn/CodeSystem/Thuoc/ThoiGIanDung\"/>\n" +
                        "                        <code value=\"4\"/>\n" +
                        "                    </coding>\n" +
                        "                </category>\n" +
                        "                <dosage>\n" +
                        "                    <rateQuantity>\n" +
                        "                        <value value=\"0\"/>\n" +
                        "                    </rateQuantity>\n" +
                        "                </dosage>\n" +
                        "                <medicationReference>\n" +
                        "                    <reference value=\"Medication/85310908-96a3-4a14-a4cf-a802ebf5faba\"/>\n" +
                        "                </medicationReference>\n" +
                        "            </MedicationAdministration>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:85310908-96a3-4a14-a4cf-a802ebf5faba\"/>\n" +
                        "        <resource>\n" +
                        "            <Medication>\n" +
                        "                <id value=\"85310908-96a3-4a14-a4cf-a802ebf5faba\"/>\n" +
                        "                <ingredient>\n" +
                        "                    <strength>\n" +
                        "                        <numerator>\n" +
                        "                            <value value=\"20\"/>\n" +
                        "                        </numerator>\n" +
                        "                    </strength>\n" +
                        "                </ingredient>\n" +
                        "                <code>\n" +
                        "                    <coding>\n" +
                        "                        <system value=\"http://icd.kcb.vn/ICD/\"/>\n" +
                        "                        <code value=\"VD-8049-09\"/>\n" +
                        "                        <display value=\"Panadol\"/>\n" +
                        "                    </coding>\n" +
                        "                </code>\n" +
                        "            </Medication>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:61503fcb-f099-4f7b-b259-861b3095350c\"/>\n" +
                        "        <resource>\n" +
                        "            <CarePlan>\n" +
                        "                <id value=\"61503fcb-f099-4f7b-b259-861b3095350c\"/>\n" +
                        "                <encounter>\n" +
                        "                    <reference value=\"Encounter/7519fdc1-92c5-488d-abd4-509780adcdbc\"/>\n" +
                        "                </encounter>\n" +
                        "                <addresses>\n" +
                        "                    <reference value=\"Condition/ac2a2f34-642a-45a7-ba6d-fe370dcd38d4\"/>\n" +
                        "                </addresses>\n" +
                        "                <activity>\n" +
                        "                    <detail>\n" +
                        "                        <productReference>\n" +
                        "                            <reference value=\"Medication/45dd2247-e8c8-49a3-9b67-f636e101d883\"/>\n" +
                        "                        </productReference>\n" +
                        "                    </detail>\n" +
                        "                </activity>\n" +
                        "            </CarePlan>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:ac2a2f34-642a-45a7-ba6d-fe370dcd38d4\"/>\n" +
                        "        <resource>\n" +
                        "            <Condition>\n" +
                        "                <id value=\"ac2a2f34-642a-45a7-ba6d-fe370dcd38d4\"/>\n" +
                        "                <extension url=\"http://dmdc.kcb.vn/extension/mattraikhongkinh\">\n" +
                        "                    <valueString value=\"0\"/>\n" +
                        "                </extension>\n" +
                        "            </Condition>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:7519fdc1-92c5-488d-abd4-509780adcdbc\"/>\n" +
                        "        <resource>\n" +
                        "            <Encounter>\n" +
                        "                <id value=\"7519fdc1-92c5-488d-abd4-509780adcdbc\"/>\n" +
                        "                <period>\n" +
                        "                    <start value=\"2021-04-19\"/>\n" +
                        "                    <end value=\"2021-04-19\"/>\n" +
                        "                </period>\n" +
                        "                <hospitalization>\n" +
                        "                    <origin>\n" +
                        "                        <reference value=\"Organization/97e33178-21dd-4d48-b3cb-e00c7e0a3bdd\"/>\n" +
                        "                    </origin>\n" +
                        "                </hospitalization>\n" +
                        "                <extension url=\"http://dmdc.kcb.vn/dmloaihinhkcb\">\n" +
                        "                    <valueString value=\"Khám bệnh\"/>\n" +
                        "                </extension>\n" +
                        "                <participant>\n" +
                        "                    <individual>\n" +
                        "                        <reference value=\"Practitioner/0002624a-5f29-4bb3-9915-c89649030215\"/>\n" +
                        "                    </individual>\n" +
                        "                </participant>\n" +
                        "                <appointment>\n" +
                        "                    <reference value=\"Appointment/8c241f0a-226d-43e9-807c-4d266762a792\"/>\n" +
                        "                </appointment>\n" +
                        "                <diagnosis>\n" +
                        "                    <condition>\n" +
                        "                        <reference value=\"Condition/533ac055-57a4-4b1a-a857-f6d6dce51300\"/>\n" +
                        "                    </condition>\n" +
                        "                </diagnosis>\n" +
                        "                <reasonReference>\n" +
                        "                    <reference value=\"Condition/08bcdbf3-9ba0-4f4c-b889-f5f78d9bef9d\"/>\n" +
                        "                </reasonReference>\n" +
                        "                <reasonReference>\n" +
                        "                    <reference value=\"Observation/24c061bb-7029-476e-aaf4-2b1169a07368\"/>\n" +
                        "                </reasonReference>\n" +
                        "                <basedOn>\n" +
                        "                    <reference value=\"ServiceRequest/beee651e-07a7-4d71-80a3-a1f324d3b321\"/>\n" +
                        "                </basedOn>\n" +
                        "                <basedOn>\n" +
                        "                    <reference value=\"ServiceRequest/527bef4b-022c-406a-82b4-1ebb05c5348a\"/>\n" +
                        "                </basedOn>\n" +
                        "            </Encounter>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:97e33178-21dd-4d48-b3cb-e00c7e0a3bdd\"/>\n" +
                        "        <resource>\n" +
                        "            <Organization>\n" +
                        "                <id value=\"97e33178-21dd-4d48-b3cb-e00c7e0a3bdd\"/>\n" +
                        "                <identifier>\n" +
                        "                    <system value=\"http://dmdc.kcb.vn/#!/dmdccosoyte\"/>\n" +
                        "                    <value value=\"10022\"/>\n" +
                        "                </identifier>\n" +
                        "            </Organization>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:0002624a-5f29-4bb3-9915-c89649030215\"/>\n" +
                        "        <resource>\n" +
                        "            <Practitioner>\n" +
                        "                <id value=\"0002624a-5f29-4bb3-9915-c89649030215\"/>\n" +
                        "                <name>\n" +
                        "                    <text value=\"Demo hồ sơ sức khỏe\"/>\n" +
                        "                </name>\n" +
                        "            </Practitioner>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:8c241f0a-226d-43e9-807c-4d266762a792\"/>\n" +
                        "        <resource>\n" +
                        "            <Appointment>\n" +
                        "                <id value=\"8c241f0a-226d-43e9-807c-4d266762a792\"/>\n" +
                        "            </Appointment>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:533ac055-57a4-4b1a-a857-f6d6dce51300\"/>\n" +
                        "        <resource>\n" +
                        "            <Condition>\n" +
                        "                <id value=\"533ac055-57a4-4b1a-a857-f6d6dce51300\"/>\n" +
                        "                <stage>\n" +
                        "                    <summary>\n" +
                        "                        <coding>\n" +
                        "                            <system value=\"http://icd.kcb.vn/ICD/\"/>\n" +
                        "                            <display value=\"A00.0 - Bệnh tả do Vibrio cholerae 01, typ sinh học cholerae, A01.0 - Thương hàn\"/>\n" +
                        "                        </coding>\n" +
                        "                    </summary>\n" +
                        "                </stage>\n" +
                        "            </Condition>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:08bcdbf3-9ba0-4f4c-b889-f5f78d9bef9d\"/>\n" +
                        "        <resource>\n" +
                        "            <Condition>\n" +
                        "                <id value=\"08bcdbf3-9ba0-4f4c-b889-f5f78d9bef9d\"/>\n" +
                        "            </Condition>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:beee651e-07a7-4d71-80a3-a1f324d3b321\"/>\n" +
                        "        <resource>\n" +
                        "            <ServiceRequest>\n" +
                        "                <id value=\"beee651e-07a7-4d71-80a3-a1f324d3b321\"/>\n" +
                        "                <code>\n" +
                        "                    <coding>\n" +
                        "                        <system value=\"http://icd.kcb.vn/ICD/\"/>\n" +
                        "                        <code value=\"K02.1904\"/>\n" +
                        "                        <display value=\"HSCC\"/>\n" +
                        "                    </coding>\n" +
                        "                </code>\n" +
                        "                <supportingInfo>\n" +
                        "                    <reference value=\"ClinicalImpression/a6d47e73-5cbc-4a2d-856b-c238da97d79e\"/>\n" +
                        "                </supportingInfo>\n" +
                        "            </ServiceRequest>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:a6d47e73-5cbc-4a2d-856b-c238da97d79e\"/>\n" +
                        "        <resource>\n" +
                        "            <ClinicalImpression>\n" +
                        "                <id value=\"a6d47e73-5cbc-4a2d-856b-c238da97d79e\"/>\n" +
                        "            </ClinicalImpression>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:527bef4b-022c-406a-82b4-1ebb05c5348a\"/>\n" +
                        "        <resource>\n" +
                        "            <ServiceRequest>\n" +
                        "                <id value=\"527bef4b-022c-406a-82b4-1ebb05c5348a\"/>\n" +
                        "                <code>\n" +
                        "                    <coding>\n" +
                        "                        <system value=\"http://icd.kcb.vn/ICD/\"/>\n" +
                        "                        <code value=\"01.0201.0849\"/>\n" +
                        "                        <display value=\"Soi đáy mắt cấp cứu\"/>\n" +
                        "                    </coding>\n" +
                        "                </code>\n" +
                        "                <supportingInfo>\n" +
                        "                    <reference value=\"ClinicalImpression/2874953a-8ce6-4eec-b822-ad5f13b96827\"/>\n" +
                        "                </supportingInfo>\n" +
                        "            </ServiceRequest>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:2874953a-8ce6-4eec-b822-ad5f13b96827\"/>\n" +
                        "        <resource>\n" +
                        "            <ClinicalImpression>\n" +
                        "                <id value=\"2874953a-8ce6-4eec-b822-ad5f13b96827\"/>\n" +
                        "            </ClinicalImpression>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:64772588-7e56-495d-a13f-32b9f898f62d\"/>\n" +
                        "        <resource>\n" +
                        "            <Condition>\n" +
                        "                <id value=\"64772588-7e56-495d-a13f-32b9f898f62d\"/>\n" +
                        "                <code>\n" +
                        "                    <coding>\n" +
                        "                        <system value=\"http://icd.kcb.vn/ICD/\"/>\n" +
                        "                        <code value=\"A00.0\"/>\n" +
                        "                        <display value=\"Bệnh tả do Vibrio cholerae 01, typ sinh học cholerae\"/>\n" +
                        "                    </coding>\n" +
                        "                </code>\n" +
                        "                <onsetDateTime value=\"2021-04-19\"/>\n" +
                        "                <encounter>\n" +
                        "                    <reference value=\"Encounter/7519fdc1-92c5-488d-abd4-509780adcdbc\"/>\n" +
                        "                </encounter>\n" +
                        "            </Condition>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:5ca437e3-a675-43ce-b029-90540bfc66ed\"/>\n" +
                        "        <resource>\n" +
                        "            <Condition>\n" +
                        "                <id value=\"5ca437e3-a675-43ce-b029-90540bfc66ed\"/>\n" +
                        "                <code>\n" +
                        "                    <coding>\n" +
                        "                        <system value=\"http://icd.kcb.vn/ICD/\"/>\n" +
                        "                        <code value=\"A01.0\"/>\n" +
                        "                        <display value=\"Thương hàn\"/>\n" +
                        "                    </coding>\n" +
                        "                </code>\n" +
                        "                <onsetDateTime value=\"2021-04-19\"/>\n" +
                        "                <encounter>\n" +
                        "                    <reference value=\"Encounter/7519fdc1-92c5-488d-abd4-509780adcdbc\"/>\n" +
                        "                </encounter>\n" +
                        "            </Condition>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:fdb45954-c77b-4b72-a807-a7d5640e1e5d\"/>\n" +
                        "        <resource>\n" +
                        "            <MedicationAdministration>\n" +
                        "                <id value=\"fdb45954-c77b-4b72-a807-a7d5640e1e5d\"/>\n" +
                        "                <dosage>\n" +
                        "                    <dose>\n" +
                        "                        <unit value=\"Viên\"/>\n" +
                        "                    </dose>\n" +
                        "                    <text value=\" Sáng: 1 Viên; Trưa: 1 Viên; Tối: 1 Viên; Trong 10 ngày\"/>\n" +
                        "                </dosage>\n" +
                        "                <medicationReference>\n" +
                        "                    <reference value=\"Medication/45dd2247-e8c8-49a3-9b67-f636e101d883\"/>\n" +
                        "                </medicationReference>\n" +
                        "            </MedicationAdministration>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:860427a6-90e2-4a8b-a991-40717b7c43ed\"/>\n" +
                        "        <resource>\n" +
                        "            <MedicationAdministration>\n" +
                        "                <id value=\"860427a6-90e2-4a8b-a991-40717b7c43ed\"/>\n" +
                        "                <category>\n" +
                        "                    <coding>\n" +
                        "                        <system value=\"http://dmdc.kcb.vn/CodeSystem/Thuoc/ThoiGIanDung\"/>\n" +
                        "                        <code value=\"1\"/>\n" +
                        "                    </coding>\n" +
                        "                </category>\n" +
                        "                <dosage>\n" +
                        "                    <rateQuantity>\n" +
                        "                        <value value=\"1\"/>\n" +
                        "                    </rateQuantity>\n" +
                        "                </dosage>\n" +
                        "                <medicationReference>\n" +
                        "                    <reference value=\"Medication/45dd2247-e8c8-49a3-9b67-f636e101d883\"/>\n" +
                        "                </medicationReference>\n" +
                        "            </MedicationAdministration>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:73079cad-a24d-4f7c-83dc-2e6338976aff\"/>\n" +
                        "        <resource>\n" +
                        "            <MedicationAdministration>\n" +
                        "                <id value=\"73079cad-a24d-4f7c-83dc-2e6338976aff\"/>\n" +
                        "                <category>\n" +
                        "                    <coding>\n" +
                        "                        <system value=\"http://dmdc.kcb.vn/CodeSystem/Thuoc/ThoiGIanDung\"/>\n" +
                        "                        <code value=\"2\"/>\n" +
                        "                    </coding>\n" +
                        "                </category>\n" +
                        "                <dosage>\n" +
                        "                    <rateQuantity>\n" +
                        "                        <value value=\"1\"/>\n" +
                        "                    </rateQuantity>\n" +
                        "                </dosage>\n" +
                        "                <medicationReference>\n" +
                        "                    <reference value=\"Medication/45dd2247-e8c8-49a3-9b67-f636e101d883\"/>\n" +
                        "                </medicationReference>\n" +
                        "            </MedicationAdministration>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:8dee60a2-0aaf-40c1-95bb-df2f15aa0f12\"/>\n" +
                        "        <resource>\n" +
                        "            <MedicationAdministration>\n" +
                        "                <id value=\"8dee60a2-0aaf-40c1-95bb-df2f15aa0f12\"/>\n" +
                        "                <category>\n" +
                        "                    <coding>\n" +
                        "                        <system value=\"http://dmdc.kcb.vn/CodeSystem/Thuoc/ThoiGIanDung\"/>\n" +
                        "                        <code value=\"3\"/>\n" +
                        "                    </coding>\n" +
                        "                </category>\n" +
                        "                <dosage>\n" +
                        "                    <rateQuantity>\n" +
                        "                        <value value=\"0\"/>\n" +
                        "                    </rateQuantity>\n" +
                        "                </dosage>\n" +
                        "                <medicationReference>\n" +
                        "                    <reference value=\"Medication/45dd2247-e8c8-49a3-9b67-f636e101d883\"/>\n" +
                        "                </medicationReference>\n" +
                        "            </MedicationAdministration>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:cf45092f-744d-49e4-886c-d739a2d7868a\"/>\n" +
                        "        <resource>\n" +
                        "            <MedicationAdministration>\n" +
                        "                <id value=\"cf45092f-744d-49e4-886c-d739a2d7868a\"/>\n" +
                        "                <category>\n" +
                        "                    <coding>\n" +
                        "                        <system value=\"http://dmdc.kcb.vn/CodeSystem/Thuoc/ThoiGIanDung\"/>\n" +
                        "                        <code value=\"4\"/>\n" +
                        "                    </coding>\n" +
                        "                </category>\n" +
                        "                <dosage>\n" +
                        "                    <rateQuantity>\n" +
                        "                        <value value=\"1\"/>\n" +
                        "                    </rateQuantity>\n" +
                        "                </dosage>\n" +
                        "                <medicationReference>\n" +
                        "                    <reference value=\"Medication/45dd2247-e8c8-49a3-9b67-f636e101d883\"/>\n" +
                        "                </medicationReference>\n" +
                        "            </MedicationAdministration>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:45dd2247-e8c8-49a3-9b67-f636e101d883\"/>\n" +
                        "        <resource>\n" +
                        "            <Medication>\n" +
                        "                <id value=\"45dd2247-e8c8-49a3-9b67-f636e101d883\"/>\n" +
                        "                <ingredient>\n" +
                        "                    <strength>\n" +
                        "                        <numerator>\n" +
                        "                            <value value=\"30\"/>\n" +
                        "                        </numerator>\n" +
                        "                    </strength>\n" +
                        "                </ingredient>\n" +
                        "                <code>\n" +
                        "                    <coding>\n" +
                        "                        <system value=\"http://icd.kcb.vn/ICD/\"/>\n" +
                        "                        <code value=\"DQG00012947\"/>\n" +
                        "                        <display value=\"  Harcotin\"/>\n" +
                        "                    </coding>\n" +
                        "                </code>\n" +
                        "            </Medication>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:283a30d6-1383-417c-b79f-e579a54b0766\"/>\n" +
                        "        <resource>\n" +
                        "            <Device>\n" +
                        "                <id value=\"283a30d6-1383-417c-b79f-e579a54b0766\"/>\n" +
                        "            </Device>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:24c061bb-7029-476e-aaf4-2b1169a07368\"/>\n" +
                        "        <resource>\n" +
                        "            <Observation>\n" +
                        "                <id value=\"24c061bb-7029-476e-aaf4-2b1169a07368\"/>\n" +
                        "                <effectiveDateTime value=\"2021-04-19\"/>\n" +
                        "                <extension url=\"http://dmdc.kcb.vn/extention/Kcb/Nguon\">\n" +
                        "                    <valueString value=\"Lịch sử KCB\"/>\n" +
                        "                </extension>\n" +
                        "                <device>\n" +
                        "                    <reference value=\"Device/283a30d6-1383-417c-b79f-e579a54b0766\"/>\n" +
                        "                </device>\n" +
                        "                <extension>\n" +
                        "                    <valueString value=\"Lịch sử KCB\"/>\n" +
                        "                </extension>\n" +
                        "                <component>\n" +
                        "                    <code>\n" +
                        "                        <coding>\n" +
                        "                            <system value=\"http://loinc.org\"/>\n" +
                        "                            <code value=\"8462-4\"/>\n" +
                        "                            <display value=\"Diastolic blood pressure\"/>\n" +
                        "                        </coding>\n" +
                        "                    </code>\n" +
                        "                    <valueQuantity>\n" +
                        "                        <value value=\"70\"/>\n" +
                        "                        <unit value=\"mmHg\"/>\n" +
                        "                        <code value=\"mm[Hg]\"/>\n" +
                        "                        <system value=\"http://unitsofmeasure.org\"/>\n" +
                        "                    </valueQuantity>\n" +
                        "                </component>\n" +
                        "                <component>\n" +
                        "                    <code>\n" +
                        "                        <coding>\n" +
                        "                            <system value=\"http://loinc.org\"/>\n" +
                        "                            <code value=\"8480-6\"/>\n" +
                        "                            <display value=\"Systolic blood pressure\"/>\n" +
                        "                        </coding>\n" +
                        "                    </code>\n" +
                        "                    <valueQuantity>\n" +
                        "                        <value value=\"133\"/>\n" +
                        "                        <unit value=\"mmHg\"/>\n" +
                        "                        <code value=\"mm[Hg]\"/>\n" +
                        "                        <system value=\"http://unitsofmeasure.org\"/>\n" +
                        "                    </valueQuantity>\n" +
                        "                </component>\n" +
                        "                <component>\n" +
                        "                    <code>\n" +
                        "                        <coding>\n" +
                        "                            <system value=\"http://loinc.org\"/>\n" +
                        "                            <code value=\"8898-9\"/>\n" +
                        "                            <display value=\"Pulse rate\"/>\n" +
                        "                        </coding>\n" +
                        "                    </code>\n" +
                        "                    <valueQuantity>\n" +
                        "                        <value value=\"65\"/>\n" +
                        "                        <unit value=\"beats/minute\"/>\n" +
                        "                        <code value=\"/min\"/>\n" +
                        "                        <system value=\"http://unitsofmeasure.org\"/>\n" +
                        "                    </valueQuantity>\n" +
                        "                </component>\n" +
                        "                <component>\n" +
                        "                    <code>\n" +
                        "                        <coding>\n" +
                        "                            <system value=\"http://loinc.org\"/>\n" +
                        "                            <code value=\"8310-5\"/>\n" +
                        "                            <display value=\"Body temperature\"/>\n" +
                        "                        </coding>\n" +
                        "                    </code>\n" +
                        "                    <valueQuantity>\n" +
                        "                        <value value=\"37.00\"/>\n" +
                        "                        <unit value=\"C\"/>\n" +
                        "                        <code value=\"Cel\"/>\n" +
                        "                        <system value=\"http://unitsofmeasure.org\"/>\n" +
                        "                    </valueQuantity>\n" +
                        "                </component>\n" +
                        "                <component>\n" +
                        "                    <code>\n" +
                        "                        <coding>\n" +
                        "                            <system value=\"http://loinc.org\"/>\n" +
                        "                            <code value=\"59408-5\"/>\n" +
                        "                            <display value=\"Oxygen saturation in Arterial blood by Pulse oximetry\"/>\n" +
                        "                        </coding>\n" +
                        "                    </code>\n" +
                        "                    <valueQuantity>\n" +
                        "                        <value value=\"13\"/>\n" +
                        "                        <unit value=\"%\"/>\n" +
                        "                        <code value=\"%\"/>\n" +
                        "                        <system value=\"http://unitsofmeasure.org\"/>\n" +
                        "                    </valueQuantity>\n" +
                        "                </component>\n" +
                        "                <component>\n" +
                        "                    <code>\n" +
                        "                        <coding>\n" +
                        "                            <system value=\"http://loinc.org\"/>\n" +
                        "                            <code value=\"9279-1\"/>\n" +
                        "                            <display value=\"Systolic blood pressure\"/>\n" +
                        "                        </coding>\n" +
                        "                    </code>\n" +
                        "                    <valueQuantity>\n" +
                        "                        <value value=\"13\"/>\n" +
                        "                        <unit value=\"beats/minute\"/>\n" +
                        "                        <code value=\"/min\"/>\n" +
                        "                        <system value=\"http://unitsofmeasure.org\"/>\n" +
                        "                    </valueQuantity>\n" +
                        "                </component>\n" +
                        "                <component>\n" +
                        "                    <code>\n" +
                        "                        <coding>\n" +
                        "                            <system value=\"http://loinc.org\"/>\n" +
                        "                            <code value=\"29463-7\"/>\n" +
                        "                            <display value=\"Body Weight\"/>\n" +
                        "                        </coding>\n" +
                        "                    </code>\n" +
                        "                    <valueQuantity>\n" +
                        "                        <value value=\"64.00\"/>\n" +
                        "                        <code value=\"[lb_av]\"/>\n" +
                        "                        <system value=\"http://unitsofmeasure.org\"/>\n" +
                        "                    </valueQuantity>\n" +
                        "                </component>\n" +
                        "            </Observation>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:1c4bbf6c-7678-428a-ad14-90baf3f65cb6\"/>\n" +
                        "        <resource>\n" +
                        "            <CarePlan>\n" +
                        "                <id value=\"1c4bbf6c-7678-428a-ad14-90baf3f65cb6\"/>\n" +
                        "                <encounter>\n" +
                        "                    <reference value=\"Encounter/38561a65-77ed-4be5-93ca-0b960c02d0fd\"/>\n" +
                        "                </encounter>\n" +
                        "                <addresses>\n" +
                        "                    <reference value=\"Condition/455b8d3e-85ae-4921-aa94-a5e9af881c5b\"/>\n" +
                        "                </addresses>\n" +
                        "            </CarePlan>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:455b8d3e-85ae-4921-aa94-a5e9af881c5b\"/>\n" +
                        "        <resource>\n" +
                        "            <Condition>\n" +
                        "                <id value=\"455b8d3e-85ae-4921-aa94-a5e9af881c5b\"/>\n" +
                        "                <bodySite>\n" +
                        "                    <coding>\n" +
                        "                        <system value=\"https://dmdc.kcb.vn/CodeSystem/CoQuan\"/>\n" +
                        "                        <code value=\"BODY_ENDOCRINES\"/>\n" +
                        "                        <display value=\"Nội tiết\"/>\n" +
                        "                    </coding>\n" +
                        "                    <text value=\"Nội tiết\"/>\n" +
                        "                </bodySite>\n" +
                        "                <bodySite>\n" +
                        "                    <coding>\n" +
                        "                        <system value=\"https://dmdc.kcb.vn/CodeSystem/CoQuan\"/>\n" +
                        "                        <code value=\"BODY_NERVES\"/>\n" +
                        "                        <display value=\"Thần kinh\"/>\n" +
                        "                    </coding>\n" +
                        "                    <text value=\"Thần kinh\"/>\n" +
                        "                </bodySite>\n" +
                        "                <bodySite>\n" +
                        "                    <coding>\n" +
                        "                        <system value=\"https://dmdc.kcb.vn/CodeSystem/CoQuan\"/>\n" +
                        "                        <code value=\"BODY_MENTALS\"/>\n" +
                        "                        <display value=\"Tâm thần\"/>\n" +
                        "                    </coding>\n" +
                        "                    <text value=\"Tâm thần\"/>\n" +
                        "                </bodySite>\n" +
                        "                <bodySite>\n" +
                        "                    <coding>\n" +
                        "                        <system value=\"https://dmdc.kcb.vn/CodeSystem/CoQuan\"/>\n" +
                        "                        <code value=\"EVALUATIONS\"/>\n" +
                        "                        <display value=\"Đánh giá\"/>\n" +
                        "                    </coding>\n" +
                        "                    <text value=\"Đánh giá\"/>\n" +
                        "                </bodySite>\n" +
                        "            </Condition>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:38561a65-77ed-4be5-93ca-0b960c02d0fd\"/>\n" +
                        "        <resource>\n" +
                        "            <Encounter>\n" +
                        "                <id value=\"38561a65-77ed-4be5-93ca-0b960c02d0fd\"/>\n" +
                        "                <period>\n" +
                        "                    <start value=\"2021-04-19\"/>\n" +
                        "                    <end value=\"2021-04-19\"/>\n" +
                        "                </period>\n" +
                        "                <hospitalization>\n" +
                        "                    <origin>\n" +
                        "                        <reference value=\"Organization/792e97f3-5adf-4e57-a7d2-db0c32f060ed\"/>\n" +
                        "                    </origin>\n" +
                        "                </hospitalization>\n" +
                        "                <extension url=\"http://dmdc.kcb.vn/dmloaihinhkcb\">\n" +
                        "                    <valueString value=\"Khám bệnh\"/>\n" +
                        "                </extension>\n" +
                        "                <participant>\n" +
                        "                    <individual>\n" +
                        "                        <reference value=\"Practitioner/a40b45ea-3db0-4114-8078-02611084a0dd\"/>\n" +
                        "                    </individual>\n" +
                        "                </participant>\n" +
                        "                <appointment>\n" +
                        "                    <reference value=\"Appointment/2f9e1a60-2a30-4c32-abb4-6e3c4a060fe9\"/>\n" +
                        "                </appointment>\n" +
                        "                <diagnosis>\n" +
                        "                    <condition>\n" +
                        "                        <reference value=\"Condition/27c9da15-17aa-4e97-97df-d3fbfdf5e235\"/>\n" +
                        "                    </condition>\n" +
                        "                </diagnosis>\n" +
                        "                <reasonReference>\n" +
                        "                    <reference value=\"Condition/dc18b318-e768-4a80-b030-3e7b9b5aedb0\"/>\n" +
                        "                </reasonReference>\n" +
                        "                <reasonReference>\n" +
                        "                    <reference value=\"Observation/c66fcac2-7c76-4d21-abf6-76cf7550b9e1\"/>\n" +
                        "                </reasonReference>\n" +
                        "            </Encounter>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:792e97f3-5adf-4e57-a7d2-db0c32f060ed\"/>\n" +
                        "        <resource>\n" +
                        "            <Organization>\n" +
                        "                <id value=\"792e97f3-5adf-4e57-a7d2-db0c32f060ed\"/>\n" +
                        "                <identifier>\n" +
                        "                    <system value=\"http://dmdc.kcb.vn/#!/dmdccosoyte\"/>\n" +
                        "                    <value value=\"10022\"/>\n" +
                        "                </identifier>\n" +
                        "            </Organization>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:a40b45ea-3db0-4114-8078-02611084a0dd\"/>\n" +
                        "        <resource>\n" +
                        "            <Practitioner>\n" +
                        "                <id value=\"a40b45ea-3db0-4114-8078-02611084a0dd\"/>\n" +
                        "                <name>\n" +
                        "                    <text value=\"Demo hồ sơ sức khỏe\"/>\n" +
                        "                </name>\n" +
                        "            </Practitioner>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:2f9e1a60-2a30-4c32-abb4-6e3c4a060fe9\"/>\n" +
                        "        <resource>\n" +
                        "            <Appointment>\n" +
                        "                <id value=\"2f9e1a60-2a30-4c32-abb4-6e3c4a060fe9\"/>\n" +
                        "            </Appointment>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:27c9da15-17aa-4e97-97df-d3fbfdf5e235\"/>\n" +
                        "        <resource>\n" +
                        "            <Condition>\n" +
                        "                <id value=\"27c9da15-17aa-4e97-97df-d3fbfdf5e235\"/>\n" +
                        "                <stage>\n" +
                        "                    <summary>\n" +
                        "                        <coding>\n" +
                        "                            <system value=\"http://icd.kcb.vn/ICD/\"/>\n" +
                        "                            <display value=\"A00 - Bệnh tả\"/>\n" +
                        "                        </coding>\n" +
                        "                    </summary>\n" +
                        "                </stage>\n" +
                        "            </Condition>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:dc18b318-e768-4a80-b030-3e7b9b5aedb0\"/>\n" +
                        "        <resource>\n" +
                        "            <Condition>\n" +
                        "                <id value=\"dc18b318-e768-4a80-b030-3e7b9b5aedb0\"/>\n" +
                        "            </Condition>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "    <entry>\n" +
                        "        <fullUrl value=\"urn:uuid:c3ba1c7a-b0c0-4f98-ac61-c09cf413bea9\"/>\n" +
                        "        <resource>\n" +
                        "            <Condition>\n" +
                        "                <id value=\"c3ba1c7a-b0c0-4f98-ac61-c09cf413bea9\"/>\n" +
                        "                <code>\n" +
                        "                    <coding>\n" +
                        "                        <system value=\"http://icd.kcb.vn/ICD/\"/>\n" +
                        "                        <code value=\"A00\"/>\n" +
                        "                        <display value=\"Bệnh tả\"/>\n" +
                        "                    </coding>\n" +
                        "                </code>\n" +
                        "                <onsetDateTime value=\"2021-04-19\"/>\n" +
                        "                <encounter>\n" +
                        "                    <reference value=\"Encounter/38561a65-77ed-4be5-93ca-0b960c02d0fd\"/>\n" +
                        "                </encounter>\n" +
                        "            </Condition>\n" +
                        "        </resource>\n" +
                        "    </entry>\n" +
                        "</Bundle>";
            }
        });
        MockitoAnnotations.initMocks(this);
        String PID ="0420540030";
        Integer patientId = 1;
        MedicalHealthcareHistoriesEntity entity = new MedicalHealthcareHistoriesEntity();
        entity.setHistoriesId(1);
        Mockito.when(medicalHealthcareHistoriesRepositoryJPA.save(Mockito.any())).thenReturn(entity);
        String token = "token";
        String phoneNumber = "0909090909";
        RequestSyncHsskDTO dto = new RequestSyncHsskDTO(1L, 2L, PID, token, patientId,phoneNumber);
        //call service
        service.getPatientHistoryInfo(dto);

    }
}