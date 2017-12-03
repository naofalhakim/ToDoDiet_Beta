package com.motionlaboratory.tododiet.DatabaseConfig;

/**
 * Created by naofal on 7/20/2017.
 */

public class ConnectionDb {
    public static final String WEB_URL = "http://tododiet.000webhostapp.com/";
    public static final String INSERT_DOCTOR = WEB_URL+"createDoctor.php";
    public static final String INSERT_PATIENT = WEB_URL+"createPatient.php";
    public static final String INSERT_EXERCISE = WEB_URL+"createExercise.php";
    public static final String INSERT_FOOD = WEB_URL+"createFood.php";
    public static final String INSERT_QUOTE = WEB_URL+"createQuote.php";
    public static final String INSERT_BCA = WEB_URL+"createBCA.php";

    public static final String LOGIN_DOCTOR = WEB_URL+"loginDokter.php";

    public static final String SHOW_PATIENT = WEB_URL+"readPatient.php";
    public static final String SHOW_PATIENT_DETAIL = WEB_URL+"readPatientDetail.php";
    public static final String SHOW_PATIENT_BADGE = WEB_URL+"readPatientBadge.php";
    public static final String SHOW_PATIENT_STATISTIC = WEB_URL+"readPatientStatistic.php";
    public static final String SHOW_PATIENT_EXERCISE = WEB_URL+"readPatientExercise.php";
    public static final String SHOW_PATIENT_FOOD = WEB_URL+"readPatientFood.php";
    public static final String SHOW_QUOTE = WEB_URL+"readQuote.php";
    public static final String SHOW_BCA = WEB_URL+"readBCA.php";
    public static final String SHOW_TASK_RESULT = WEB_URL+"readPatientTaskResult.php";

}
