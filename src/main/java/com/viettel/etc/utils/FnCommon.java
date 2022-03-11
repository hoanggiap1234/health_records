package com.viettel.etc.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.squareup.okhttp.*;
import com.viettel.etc.controllers.MedicalHealthcareAllergyController;
import com.viettel.etc.dto.*;
import com.viettel.etc.xlibrary.core.constants.FunctionCommon;
import com.viettel.etc.xlibrary.core.entities.MessEntity;
import com.viettel.etc.xlibrary.core.entities.ResponseEntity;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.keycloak.KeycloakPrincipal;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * Phuong thuc chung cho toan bo project
 */
public class FnCommon extends FunctionCommon {
	private static final Logger LOGGER = Logger.getLogger(FnCommon.class);
	private static final ResourceBundle RESOURCE_BUNDLE = getResource();

	private static ResourceBundle getResource() {
		try {
			ResourceBundle appConfigRB = ResourceBundle.getBundle(
					Constants.FILE_MESS);
			return appConfigRB;
		} catch (Exception e) {
			LOGGER.info(e);
			LOGGER.error("Loi! getResourceBundle: " + e.getMessage());
		}
		return null;
	}

	public static LocalDateTime toLocalDateTime(Date dateToConvert) {
		return dateToConvert.toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDateTime();
	}

	/**
	 * Convert date date to string date
	 *
	 * @param date
	 * @param isFullDateTime:true: full date time, false: date sort
	 * @return
	 */
	public static String convertDateToString(Date date, Boolean isFullDateTime) {
		String strDate;
		if (date == null) {
			return "";
		}
		if (isFullDateTime) {
			strDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(date);
		} else {
			strDate = new SimpleDateFormat("dd/MM/yyyy").format(date);
		}
		return strDate;
	}

	/**
	 * Go bo dau tieng viet
	 *
	 * @param s
	 * @return
	 */
	public static String removeAccent(String s) {
		if (s == null) {
			return "";
		}
		String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(temp).replaceAll("").replace("đ", "d").replace("Đ", "D");
	}

	/**
	 * doc file properties trong cau hinh thu muc default
	 *
	 * @param key
	 * @return
	 */
	public static String getValueFileMess(String key) {
		String value = RESOURCE_BUNDLE.containsKey(key)
				? RESOURCE_BUNDLE.getString(key)
				: Constants.STR_EMTY;
		if (value.trim().length() <= 0) {
			LOGGER.error("Not value with key:" + key + ", in file properties");
		}
		return value;
	}

	/**
	 * tra du lieu ve client
	 *
	 * @param errorApp
	 * @param itemObject
	 * @return
	 */
	public static Object responseToClient(ErrorApp errorApp, Object itemObject) {
		ResponseEntity responseEntity = new ResponseEntity();
		if (itemObject != null) {
			responseEntity.setData(itemObject);
		}
		MessEntity itemEntity = new MessEntity();
		itemEntity.setCode(errorApp.getCode());
		itemEntity.setDescription(errorApp.getDescription());
		responseEntity.setMess(itemEntity);
		return responseEntity;
	}

	/**
	 * tra ve client ma loi va du lieu theo ma loi
	 *
	 * @param etcException
	 * @param itemObject
	 * @return
	 */
	public static Object responseToClient(TeleCareException etcException, Object itemObject) {
		return responseToClient(etcException.getErrorApp(), itemObject);
	}

	/**
	 * tra ve client thong bao ma loi
	 *
	 * @param etcException
	 * @return
	 */
	public static Object responseToClient(TeleCareException etcException) {
		return responseToClient(etcException.getErrorApp(), null);
	}

	public static Object responseToClient(ErrorApp errorApp) {
		ResponseEntity responseEntity = new ResponseEntity();
		MessEntity itemEntity = new MessEntity();
		itemEntity.setCode(errorApp.getCode());
		itemEntity.setDescription(errorApp.getDescription());
		responseEntity.setMess(itemEntity);
		return responseEntity;
	}

	public static Object responseToClient(MessageDTO messageDTO) {
		ResponseEntity responseEntity = new ResponseEntity();
		responseEntity.setData(null);
		ErrorMsgEntity itemEntity = new ErrorMsgEntity();
		if (Objects.nonNull(messageDTO)) {
			itemEntity.setCode(messageDTO.getCode());
			itemEntity.setDescription(messageDTO.getDescription());
			itemEntity.setMessageCode(messageDTO.getMessageCode());
		}
		responseEntity.setMess(itemEntity);
		return responseEntity;
	}
	public static Object responseToClient(MessageDTO messageDTO, String resultDescription) {
		ResponseEntity responseEntity = new ResponseEntity();
		responseEntity.setData(resultDescription);
		ErrorMsgEntity itemEntity = new ErrorMsgEntity();
		if (Objects.nonNull(messageDTO)) {
			itemEntity.setCode(messageDTO.getCode());
			itemEntity.setDescription(messageDTO.getDescription());
			itemEntity.setMessageCode(messageDTO.getMessageCode());
		}
		responseEntity.setMess(itemEntity);
		return responseEntity;
	}

	/**
	 * Thuc hien nem loi thong bao ra ngoai
	 *
	 * @param errorApp
	 * @throws TeleCareException
	 */
	public static void throwsErrorApp(ErrorApp errorApp) throws TeleCareException {
		throw new TeleCareException(errorApp);
	}

	/**
	 * Convert class to json string
	 *
	 * @param object
	 * @return
	 */
	public static String toStringJson(Object object) throws TeleCareException {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(object);
		} catch (Exception e) {
			LOGGER.info(e);
			throw new TeleCareException(ErrorApp.ERR_JSON_PARSE);
		}
	}

	/**
	 * Gui request den server keycloak
	 *
	 * @param url
	 * @param token
	 * @param requestBody
	 * @return
	 */
	public static Response doPutRequest(String url, String token, RequestBody requestBody) {
		long timeOut = Long.parseLong(FunctionCommon.getPropertiesValue("ocs_timeout"));
		OkHttpClient client = new OkHttpClient();
		try {
			client.setConnectTimeout(timeOut, TimeUnit.SECONDS);
			client.setReadTimeout(30, TimeUnit.SECONDS);
			client.setWriteTimeout(30, TimeUnit.SECONDS);
			HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
			Request request = new Request.Builder()
					.header("Accept", "application/json")
					.header("Authorization", "Bearer " + token)
					.url(httpBuilder.build())
					.put(requestBody)
					.build();
			return client.newCall(request).execute();
		} catch (Exception e) {
			LOGGER.error("Has error", e);
		}
		return null;
	}

	public static Response doPostRequest(String url, String token, RequestBody requestBody) throws TeleCareException {
		long timeOut = Long.parseLong(FunctionCommon.getPropertiesValue("ocs_timeout"));
		OkHttpClient client = new OkHttpClient();
		try {
			client.setConnectTimeout(timeOut, TimeUnit.SECONDS);
			client.setReadTimeout(30, TimeUnit.SECONDS);
			client.setWriteTimeout(30, TimeUnit.SECONDS);
			HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
			Request request = new Request.Builder()
					.header("Accept", "application/json")
					.header("Authorization", "Bearer " + token)
					.url(httpBuilder.build())
					.post(requestBody)
					.build();
			return client.newCall(request).execute();
		} catch (Exception e) {
			LOGGER.error("Has error", e);
			e.printStackTrace();
		}
		return null;
	}

	public static String generationPasswordApp() {
		return String.valueOf(100000 + new Random().nextInt(900000));
	}

	public static String[] getNullPropertyNames(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<String>();
		for (java.beans.PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null) {
				emptyNames.add(pd.getName());
			}
		}

		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}

	public static void copyProperties(Object src, Object target) {
		BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
	}

	/**
	 * Lay token admin
	 *
	 * @return
	 */
	public static String getAdminToken() {
		long timeOut = Long.parseLong(FunctionCommon.getPropertiesValue("ws.timeout"));
		OkHttpClient client = new OkHttpClient();
		AdminDTO adminAccount = new AdminDTO();
		RequestBody body = RequestBody.create(Constants.JSON_TOKEN, adminAccount.toString());
		try {
			client.setConnectTimeout(timeOut, TimeUnit.SECONDS);
			client.setReadTimeout(30, TimeUnit.SECONDS);
			client.setWriteTimeout(30, TimeUnit.SECONDS);
			HttpUrl.Builder httpBuilder = HttpUrl.parse(System.getenv("Keycloak") + FnCommon.getPropertiesValue("ws.keycloak.login")).newBuilder().username(adminAccount.getUsername()).password(adminAccount.getPassword());
			Request request = new Request.Builder()
					.header("Accept", "*/*")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.header("Accept-Encoding", "gzip, deflate, br")
					.url(httpBuilder.build())
					.post(body)
					.build();
			Response response = client.newCall(request).execute();
			ResponseDTO responseDTO;
			if (response != null) {
				responseDTO = new Gson().fromJson(response.body().string(), ResponseDTO.class);
				return responseDTO.getAccess_token();
			}
		} catch (Exception e) {
			LOGGER.error("Has error", e);
		}
		return null;
	}


	/**
	 * lay id thong tin nguoi dung
	 *
	 * @param authentication
	 * @return
	 */
	public static String getUserIdLogin(Authentication authentication) {
		try {
			KeycloakPrincipal principal = (KeycloakPrincipal) authentication.getPrincipal();
			String userId = principal.getKeycloakSecurityContext().getToken().getSubject();
			return userId;
		} catch (Exception e) {
			LOGGER.info(e);
			LOGGER.error("Loi! getUserLogin: " + e.getMessage());
			return null;
		}
	}

//    public static boolean validateDate(LocalDate date) {
//        if (date == null) {
//            return false;
//        }
//        return true;
//    }

	public static boolean isNull(Date date) {
		if (date == null) {
			return true;
		}
		return false;
	}

//	public static String formatLocalDateTime(LocalDateTime localDateTime, String pattern) {
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
//		String formattedDateTime = localDateTime.format(formatter);
//		return formattedDateTime;
//	}
//
//	public static boolean isNullFieldObject(Object object) {
//		String[] fieldNameNull = getNullPropertyNames(object);
//		if (fieldNameNull.length != object.getClass().getDeclaredFields().length) {
//			return false;
//		}
//		return true;
//	}
//
//	/**
//	 * Upload file
//	 *
//	 * @param containerFolder
//	 * @param file
//	 * @return
//	 * @throws IOException
//	 */
//	public static String uploadFile(String containerFolder, MultipartFile file) {
//		try {
//			if (file.isEmpty() || Objects.isNull(containerFolder)) {
//				return null;
//			}
//			if (!Files.exists(Paths.get(containerFolder))) {
//				Files.createDirectories(Paths.get(containerFolder));
//			}
//
//			byte[] bytes = file.getBytes();
//			String relativePath = containerFolder + UUID.randomUUID() + "_" + file.getOriginalFilename();
//			Path path = Paths.get(relativePath);
//			Files.write(path, bytes);
//
//			return ServletUriComponentsBuilder.fromPath(relativePath)
//					.toUriString();
//		} catch (Exception e) {
//			LOGGER.error("Loi! uploadFile: " + e.getMessage());
//		}
//
//		return null;
//	}
//
//	public static boolean checkBriefcaseValid(String fileName, byte[] file, Integer maxFileSizeMb) {
//		if (Objects.isNull(maxFileSizeMb)) {
//			maxFileSizeMb = 5;
//		}
//		Objects.requireNonNull(file);
//		long fileSizeMb = file.length / (1024 * 1024);
//		return checkFileExtensionValid(fileName, ".JPG", ".PNG", ".TIFF", ".BMP", ".PDF") && fileSizeMb <= maxFileSizeMb;
//	}

	public static boolean checkFileExtensionValid(String fileName, String... fileExtensions) {
		//TODO: throw exception to client
		Objects.requireNonNull(fileName);
		for (String fileExtension : fileExtensions) {
			if (fileExtension != null && !fileExtension.isEmpty() && fileName.toLowerCase().endsWith(fileExtension.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

//	/**
//	 * Kiem tra file da ton tai chua
//	 *
//	 * @param containerFolder
//	 * @return
//	 * @throws IOException
//	 */
//	public static boolean createFolder(String containerFolder) {
//		if (!Files.exists(Paths.get(containerFolder))) {
//			try {
//				Files.createDirectories(Paths.get(containerFolder));
//				return true;
//			} catch (IOException e) {
//				LOGGER.error("Loi! uploadFile: " + e.getMessage());
//			}
//		}
//		return false;
//	}

	/**
	 * sanity check a date
	 *
	 * @param time Timestamp in milliseconds
	 * @return true is date, false not date
	 */
	public static boolean isDate(Long time) {
		Calendar cal = Calendar.getInstance();
		cal.setLenient(false);
		try {
			cal.setTimeInMillis(time);
			cal.getTime();
			return true;
		} catch (Exception e) {
			LOGGER.error("err date:" + e.getMessage(), e);
		}
		return false;
	}

	/**
	 * convert Timestamp in milliseconds to local date
	 *
	 * @param dateToConvert Timestamp in milliseconds
	 * @return local with Timestamp
	 */
	public static LocalDate convertToLocalDate(Long dateToConvert) {
		return Instant.ofEpochMilli(dateToConvert).atZone(ZoneId.systemDefault()).toLocalDate();
	}

//	public static Date convertToDate(LocalDate dateToConvert) {
//		return java.util.Date.from(dateToConvert.atStartOfDay()
//				.atZone(ZoneId.systemDefault())
//				.toInstant());
//	}

	public static int getDaysBetweenDates(Date fromDate, Date toDate) {
		LocalDate from = FnCommon.convertToLocalDate(fromDate.getTime());
		LocalDate to = FnCommon.convertToLocalDate(toDate.getTime());
		long days = ChronoUnit.DAYS.between(from, to);
		return (int) days;
	}

//	public static String formatLocalDate(LocalDate localDate, String pattern) {
//		return localDate.format(DateTimeFormatter.ofPattern(pattern));
//	}

	public static TelecareUserEntity getTelecareUserInfo(Authentication authentication) {
		try {
			KeycloakPrincipal principal = (KeycloakPrincipal) authentication.getPrincipal();
			String userId = principal.getKeycloakSecurityContext().getToken().getSubject();

			String[] split_string = principal.getKeycloakSecurityContext().getTokenString().split("\\.");
			if (split_string.length <= 0) {
				return null;
			} else {
				String base64EncodedBody = split_string[1];
				org.apache.commons.codec.binary.Base64 base64Url = new Base64(true);
				String body = new String(base64Url.decode(base64EncodedBody));
				TelecareUserEntity authen = (TelecareUserEntity) FunctionCommon.convertJsonToObject(body, TelecareUserEntity.class);
				authen.setStrJwtToken(principal.getKeycloakSecurityContext().getTokenString());
				authen.setKeycloakUserId(userId);

				if (authen != null) {
					authen.setIsVerifyToken(true);
				}

				return authen;
			}
		} catch (Exception e) {
			LOGGER.error("Loi! getUserInfo: " + e.getMessage(), e);
		}
		return null;
	}

	/**
	 * Lay token HSSK
	 *
	 * @return
	 */
	public static String getHSSKToken() {
		long timeOut = Long.parseLong(FnCommon.getPropertiesValue("ws.timeout"));
		OkHttpClient client = new OkHttpClient();
		AccountHSSKDTO adminHSSKDTO = new AccountHSSKDTO();
		RequestBody body = RequestBody.create(Constants.JSON_TOKEN, adminHSSKDTO.toString());
		try {
			client.setConnectTimeout(timeOut, TimeUnit.SECONDS);
			client.setReadTimeout(30, TimeUnit.SECONDS);
			client.setWriteTimeout(30, TimeUnit.SECONDS);
			HttpUrl.Builder httpBuilder = HttpUrl.parse(System.getenv("HSSK_KEYCLOAK") + "/auth/realms/hsskv2/protocol/openid-connect/token").newBuilder();
			Request request = new Request.Builder()
					.header("Accept", "*/*")
					.header("Content-Type","application/x-www-form-urlencoded")
					.header("Accept-Encoding", "gzip, deflate, br")
					.url(httpBuilder.build())
					.post(body)
					.build();
			Response response = client.newCall(request).execute();
			ResponseDTO responseDTO;
			if (response != null) {
				responseDTO = new Gson().fromJson(response.body().string(), ResponseDTO.class);
				return responseDTO.getAccess_token();
			}
		} catch (Exception e) {
			LOGGER.error("Has error", e);
		}
		return null;
	}

	public static Response doGetRequest(String url, String token) {
		long timeOut = 30;
		OkHttpClient client = new OkHttpClient();
		try {
			client.setConnectTimeout(timeOut, TimeUnit.SECONDS);
			client.setReadTimeout(30, TimeUnit.SECONDS);
			client.setWriteTimeout(30, TimeUnit.SECONDS);
			HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
			Request request = new Request.Builder()
					.header("Authorization", "Bearer " + token)
					.header("Content-Type", "application/json")
					.header("Accept", "*/*")
					.header("Accept-Encoding", "gzip, deflate, br")
					.header("Connection", "keep-alive")
					.url(httpBuilder.build())
					.get()
					.build();
			return client.newCall(request).execute();
		} catch (Exception e) {
			LOGGER.error("Has error", e);
		}
		return null;
	}

	public static Response doGetRequestTemp(String url) {
		long timeOut = 30;
		OkHttpClient client = new OkHttpClient();
		try {
			client.setConnectTimeout(timeOut, TimeUnit.SECONDS);
			client.setReadTimeout(30, TimeUnit.SECONDS);
			client.setWriteTimeout(30, TimeUnit.SECONDS);
			HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
			Request request = new Request.Builder()
//					.header("Authorization", "Bearer " + token)
					.header("Content-Type", "application/json")
					.header("Accept", "*/*")
					.header("Accept-Encoding", "gzip, deflate, br")
					.header("Connection", "keep-alive")
					.url(httpBuilder.build())
					.get()
					.build();
			return client.newCall(request).execute();
		} catch (Exception e) {
			LOGGER.error("Has error", e);
		}
		return null;
	}

	public static void classifyCreateOrUpdateOrDelete(Collection<?> hsskEntities, Collection<?> oldEntities) throws NoSuchFieldException, IllegalAccessException {
		if(oldEntities.size()==0 || hsskEntities.size()==0) {
			return;
		}
		//have to override equals and hashcode
		for (Object oldEntity : oldEntities) {
			for (Iterator<?> iterator1 = hsskEntities.iterator(); iterator1.hasNext(); ) {
				Object hsskEntity = iterator1.next();
				if (oldEntity.equals(hsskEntity)) {
					copyPropertiesExcludingCreateDateAndIsSync(hsskEntity, oldEntity);
					iterator1.remove();
					break;
				}
//				else {
//					updateIsDelete(oldEntity, Constants.IS_DELETE);
//				}
			}
		}
	}

	public static Object classifyCreateOrUpdate(Object hsskEntity, Collection<?> oldEntities) throws NoSuchFieldException, IllegalAccessException {
		//have to override equals and hashcode
		for(Object oldEntity : oldEntities) {
			if(hsskEntity.equals(oldEntity)) {
				FnCommon.copyPropertiesExcludingCreateDateAndIsSync(hsskEntity, oldEntity);
				return oldEntity;
			}
		}
		return hsskEntity;
	}

	private static void updateIsDelete(Object oldEntity, Integer isDelete) throws NoSuchFieldException, IllegalAccessException {
		Field field = oldEntity.getClass().getDeclaredField("isDelete");
		field.setAccessible(true);
		field.set(oldEntity, isDelete);
	}

	private static void copyPropertiesExcludingCreateDateAndIsSync(Object hsskEntity, Object oldEntity) throws NoSuchFieldException, IllegalAccessException {
		Field field = oldEntity.getClass().getDeclaredField("createDate");
		field.setAccessible(true);
		Field field1 = oldEntity.getClass().getDeclaredField("isSync");
		field1.setAccessible(true);
		Date createdDate = (Date) field.get(oldEntity);
		Object isSync = field1.get(oldEntity);
		FnCommon.copyProperties(hsskEntity, oldEntity);
		field.set(oldEntity, createdDate);
		if(Objects.equals(isSync, 0) || Objects.equals(isSync, false)) {
			field1.set(oldEntity, isSync);
		}
	}

	/**
	 * Lay token hssk
	 *
	 * @return
	 */
	public static String getTokenHSSK() throws TeleCareException {
		long timeOut = Long.parseLong(FunctionCommon.getPropertiesValue("ws.timeout"));
		OkHttpClient client = new OkHttpClient();
		ClientHsskDTO hsskAccount = new ClientHsskDTO();
		RequestBody body = RequestBody.create(Constants.JSON, FnCommon.toStringJson(hsskAccount));
		try{
			client.setConnectTimeout(timeOut, TimeUnit.SECONDS);
			client.setReadTimeout(30, TimeUnit.SECONDS);
			client.setWriteTimeout(30, TimeUnit.SECONDS);
			HttpUrl.Builder httpBuilder = HttpUrl.parse(System.getenv("HSSK_VACCINES_URL") +
					FnCommon.getPropertiesValue("ws.hssk.login")).newBuilder();
			Request request = new Request.Builder()
					.header("Content-Type", "application/json")
					.url(httpBuilder.build())
					.post(body)
					.build();
			Response response = client.newCall(request).execute();
			if (response != null) {
				ResponseDTO responseDTO = new Gson().fromJson(response.body().string(), ResponseDTO.class);
				return responseDTO.getData().getAccess_token();
			}
		} catch (IOException e) {
			LOGGER.error("Has error", e);
		}
		return null;
	}

	public static Integer toExamTypeId(String examType) {
		if(examType==null || "Khám bệnh".equals(examType)) {
			return 1;
		} else if("Điều trị ngoại trú".equals(examType)) {
			return 2;
		} else if("Điều trị nội trú".equals(examType)) {
			return 3;
		} else if("KCB từ xa".equals(examType)) {
			return 0;
		} else {
			return -1;
		}
	}

	public static Date convertLocalToDate(LocalDateTime ldt) {
		return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
	}
}
