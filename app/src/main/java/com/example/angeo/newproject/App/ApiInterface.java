package com.example.angeo.newproject.App;

//import info.androidhive.retrofit.model.MoviesResponse;


import com.example.angeo.newproject.Retrofit.CreateRydeBike.BikePool;
import com.example.angeo.newproject.Retrofit.FindRyde.FindRydeModule;
import com.example.angeo.newproject.Retrofit.HomePage.HomeModule;
import com.example.angeo.newproject.Retrofit.LoginUser.LoginUser;
import com.example.angeo.newproject.Retrofit.ProfileData.ProfileDatasModule;
import com.example.angeo.newproject.Retrofit.RegisterUser.RegisterUserModule;
import com.example.angeo.newproject.Retrofit.RideStatus.RideStatusModul;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
//    @GET("movie/top_rated")
//    Call<HomeResponse> getHomeResponse(@Query("api_key") String apiKey);


    @GET("{path}")
    Call<RegisterUserModule> getregister(@Path("path") String path, @Query("name") String name,
                                         @Query("username") String lastname, @Query("gender") String gender, @Query("email") String email,
                                         @Query("password") String password, @Query("contact") String contact);
    @GET("{path}")
    Call<ProfileDatasModule> getprofile(@Path("path") String path, @Query("userid") String userid,
                                        @Query("contact") String contact);

    @GET("{path}")
    Call<LoginUser> getlogin(@Path("path") String path, @Query("username") String username,
                             @Query("password") String password);
    @GET("{path}")
    Call<RideStatusModul> getupdateuser(@Path("path") String path, @Query("userid") String userid,
                                        @Query("latitude") double latitude, @Query("longitude") double longitude, @Query("finduserid") String finduserid);
    @GET("{path}")
    Call<HomeModule> gethome(@Path("path") String path);



    @GET("{path}")
    Call<BikePool> getbikepool(@Path("path") String path, @Query("userid") String userid, @Query("model") String model,
                               @Query("regno") String regno, @Query("startlat") String startlat, @Query("startlon") String startlon,
                               @Query("destlat") String destlat, @Query("destlon") String destlon, @Query("offerdate") String offerdate,
                               @Query("offertime") String offertime, @Query("price") String price);

    //
    @GET("{path}")
    Call<FindRydeModule> getfindryde(@Path("path") String path);
//

//    @GET("{path}")
//    Call<RRelationship> getRelationshipResponse(@Path("path") String path);
//
//    @GET("{path}")
//    Call<HomeModule> getOtp(@Path("path") String path, @Query("phoneNo") String phone, @Query("otherPhoneNo") String studentID, @Query("relationshipID") String relationshipID, @Query("name") String name);
//
//    @GET("{path}")
//    Call<Fetch_Example> getfetchuser(@Path("path") String path, @Query("phoneNo") String phone);
//
//    @GET("{path}")
//    Call<Register_Update> getUpdateRegister(@Path("path") String path, @Query("phoneNo") String phone, @Query("userEmail")
//            String userEmail, @Query("userPassword") String userPassword);
//
//    @GET("{path}")
//    Call<Students_Data> getStudentsData(@Path("path") String path, @Query("phoneNo") String phone);
//
//    @GET("{path}")
//    Call<StudentPaymentModule> getstudetpaymentmodule(@Path("path") String path, @Query("phoneNo") String phone, @Query("studentID") String studentID);
//
//    @GET("{path}")
//    Call<Cardrecharge_enable> getcard_enable(@Path("path") String path, @Query("studentID") String studentId, @Query("phoneNo") String phoneNo, @Query("requestDevice") String requestDevice);
//
//    @GET("{path}")
//    Call<FeepaymentEnable_Check> getfee_enable(@Path("path") String path, @Query("studentID") String studentId, @Query("phoneNo") String phoneNo, @Query("requestDevice") String requestDevice);
//
//    @GET("{path}")
//    Call<Miscellanous_Enabled> getmiss_enable(@Path("path") String path, @Query("studentID") String studentId, @Query("phoneNo") String phoneNo, @Query("requestDevice") String requestDevice);
//
//    @GET("{path}")
//    Call<RazorpayTrans> getrazorpay(@Path("path") String path, @Query("phoneNo") String phoneNo, @Query("studentID") String studentID, @Query("amount") String amount, @Query("transaction_category") String transaction_category);
//
//    @GET("{path}")
//    Call<OnlineTransactions> getonlinetrans(@Path("path") String path, @Query("phoneNo") String phoneNo, @Query("studentID") String studentID, @Query("transaction_category") String transaction_category);
//
//    @GET("{path}")
//    Call<TransactionPayumoney> gettranspayumoney(@Path("path") String path, @Query("phoneNo") String phoneNo, @Query("studentID") String studentID, @Query("amount") String amount, @Query("transaction_category") String transaction_category);
//
//    @GET("{path}")
//    Call<TransactctionTecProcess> gettranstechprocess(@Path("path") String path, @Query("phoneNo") String phoneNo, @Query("studentID") String studentID, @Query("amount") String amount, @Query("transaction_category") String transaction_category);
//
//    @GET("{path}")
//    Call<R_StudentFeePayments_> getstudentfeepayments(@Path("path") String path, @Query("phoneNo") String phoneNo, @Query("studentID") String studentID);
//
//    @GET("{path}")
//    Call<C_fee_transaction_techprocess_android> getfeetransTechprocess(@Path("path") String path, @Query("phoneNo") String phoneNo, @Query("studentID") String studentID, @Query("amount") String amount, @Query("transaction_category") String transaction_category, @Query("schoolFeeTermID") String schoolFeeTermID);
//
//    @GET("{path}")
//    Call<Fee_Razorpay> getfeerazorpay(@Path("path") String path, @Query("phoneNo") String phoneNo, @Query("studentID") String studentID, @Query("amount") String amount, @Query("transaction_category") String transaction_category, @Query("schoolFeeTermID") String schoolFeeTermID);
//
//    @GET("{path}")
//    Call<TechProcessStatus> gettechprostatus(@Path("path") String path, @Query("phoneNo") String phoneNo, @Query("studentID") String studentID, @Query("tran_id") String tran_id, @Query("transaction_category") String transaction_category, @Query("tran_status") String tran_status);
//
//    @GET("{path}")
//    Call<FeeHistory> getfeepay(@Path("path") String path, @Query("phoneNo") String phoneNo, @Query("studentID") String studentID, @Query("transaction_category") String transaction_category);
//
//    @GET("{path}")
//    Call<Miscellaneous_history> getmisshistory(@Path("path") String path, @Query("phoneNo") String phoneNo, @Query("studentID") String studentID, @Query("transaction_category") String transaction_category);
//
//    @GET("{path}")
//    Call<Miss_Stud_Paymnt> getmisspaystudnts(@Path("path") String path, @Query("phoneNo") String phoneNo, @Query("studentID") String studentID);
//
//    @GET("{path}")
//    Call<MissPayRazorpay> getmisspayrazorpay(@Path("path") String path, @Query("phoneNo") String phoneNo, @Query("studentID") String studentID, @Query("amount") String amount, @Query("transaction_category") String transaction_category, @Query("schoolMiscellaneousPaymentID") String schoolMiscellaneousPaymentID);
//
//    @GET("{path}")
//    Call<MissTransTechProcess> getmisstrnstech(@Path("path") String path, @Query("phoneNo") String phoneNo, @Query("studentID") String studentID, @Query("amount") String amount, @Query("transaction_category") String transaction_category, @Query("schoolMiscellaneousPaymentID") String schoolMiscellaneousPaymentID);
//
//    @GET("{path}")
//    Call<CalendarPhp> getcalendar(@Path("path") String path, @Query("phoneNo") String phoneNo, @Query("studentID") String studentID, @Query("year") int year, @Query("month") int month);
//
//    @GET("{path}")
//    Call<CalendarClick> getcalendarclick(@Path("path") String path, @Query("phoneNo") String phoneNo, @Query("studentID") String studentID, @Query("date") String date);
//
//    @GET("{path}")
//    Call<TimeTableListing> gettimetable(@Path("path") String path, @Query("phoneNo") String phoneNo, @Query("studentID") String studentID, @Query("day") String day);
//
//    @GET("{path}")
//    Call<LeaveRquestList> getleavlist(@Path("path") String path, @Query("phoneNo") String phoneNo, @Query("studentID") String studentID);
//
//    @GET("{path}")
//    Call<LeavCreate> getleavcreate(@Path("path") String path, @Query("phoneNo") String phoneNo, @Query("studentID") String studentID, @Query("leaveRequestFromDate") String leaveRequestFromDate, @Query("leaveRequestToDate") String leaveRequestToDate, @Query("leaveRequestReason") String leaveRequestReason);
//
//    @GET("{path}")
//    Call<NoticeList> getnotice(@Path("path") String path, @Query("phoneNo") String phoneNo, @Query("studentID") String studentID);
//
//    @GET("{path}")
//    Call<TransactionsLists> gettrans(@Path("path") String path, @Query("phoneNo") String phoneNo, @Query("studentID") String studentID);
//
//    @GET("{path}")
//    Call<AttendanceTrans> getattndance(@Path("path") String path, @Query("phoneNo") String phoneNo, @Query("studentID") String studentID, @Query("transactionTypeID") String transactionTypeID);
//
//    @GET("{path}")
//    Call<PayFeeTrans> getpayfee(@Path("path") String path, @Query("phoneNo") String phoneNo, @Query("studentID") String studentID, @Query("transactionTypeID") String transactionTypeID);
//
//    @GET("{path}")
//    Call<CanteenTrans> getcanteen(@Path("path") String path, @Query("phoneNo") String phoneNo, @Query("studentID") String studentID, @Query("transactionTypeID") String transactionTypeID);
//
//    @GET("{path}")
//    Call<CanteenPopupTrans> getsubcanteen(@Path("path") String path, @Query("phoneNo") String phoneNo, @Query("studentID") String studentID, @Query("transactionTypeID") String transactionTypeID, @Query("transactionID") String transactionID);
//
//    @GET("{path}")
//    Call<RechargeReport> getrecharge(@Path("path") String path, @Query("phoneNo") String phoneNo, @Query("studentID") String studentID, @Query("transactionTypeID") String transactionTypeID);
//
//    @GET("{path}")
//    Call<LibraryReprtTrans> getlib(@Path("path") String path, @Query("phoneNo") String phoneNo, @Query("studentID") String studentID, @Query("transactionTypeID") String transactionTypeID);
//
//    @GET("{path}")
//    Call<SubjectsListing> getmessfrom(@Path("path") String path, @Query("phoneNo") String phoneNo, @Query("studentID") String studentID);
//
//    @GET("{path}")
//    Call<SubTeacherMsg> getmessfromsubteacher(@Path("path") String path, @Query("phoneNo") String phoneNo, @Query("studentID") String studentID, @Query("subjectID") String subjectID);
//
//    @GET("{path}")
//    Call<ClassteacherMessage> getmessfromclassteacher(@Path("path") String path, @Query("phoneNo") String phoneNo, @Query("studentID") String studentID, @Query("standardDivisionID") String standardDivisionID);
//
//    @GET("{path}")
//    Call<MessageToTeacher1> getmsgtoteacher(@Path("path") String path, @Query("phoneNo") String phoneNo, @Query("studentID") String studentID, @Query("message_type_Id") String message_type_Id);
//
//    @GET("{path}")
//    Call<CreateToTeacher> getcreateteacher(@Path("path") String path, @Query("phoneNo") String phoneNo, @Query("studentID") String studentID, @Query("message_type_Id") String message_type_Id, @Query("reason") String reason);
//
//    @GET("{path}")
//    Call<Student_Details> getstudentdetails(@Path("path") String path, @Query("phoneNo") String phoneNo, @Query("studentID") String studentID);
//
//    @GET("{path}")
//    Call<CollegeStudentDetails> getcollegestudentdetails(@Path("path") String path, @Query("phoneNo") String phoneNo, @Query("studentID") String studentID);
//
//    @GET("{path}")
//    Call<CountryLists> getcountry(@Path("path") String path, @Query("schoolID") String schoolID);
//
//    @GET("{path}")
//    Call<StatesLists> getstates(@Path("path") String path, @Query("schoolID") String schoolID, @Query("countryID") String countryID);
//
//    @GET("{path}")
//    Call<NewIdStatesList> getnewstates(@Path("path") String path, @Query("countryID") String countryID);
//
//    @GET("{path}")
//    Call<DistrictList> getdistrict(@Path("path") String path, @Query("schoolID") String schoolID, @Query("stateID") String stateID);
//
//    @GET("{path}")
//    Call<NewIdDistrictLists> getnewdistrict(@Path("path") String path, @Query("stateID") String stateID);
//
//    @GET("{path}")
//    Call<CityLists> getcity(@Path("path") String path, @Query("schoolID") String schoolID, @Query("districtID") String districtID);
//
//
//    @GET("{path}")
//    Call<NewSchoolsLists> getschools(@Path("path") String path, @Query("districtID") String districtID);
//
//    @GET("{path}")
//    Call<StandardLists> getstd(@Path("path") String path, @Query("schoolID") String schoolID);
//
//    @GET("{path}")
//    Call<DivisionsLists> getdivisions(@Path("path") String path, @Query("schoolID") String schoolID, @Query("standardID") String standardID);
//
//
//    @GET("{path}")
//    Call<NewIdCountryList> getcountrynewid(@Path("path") String path);
//
//    @GET("{path}")
//    Call<CardSuccess> getsuccess(@Path("path") String path, @Query("phoneNo") String phoneNo,
//                                 @Query("studentID") String studentID, @Query("tran_id") String tran_id,
//                                 @Query("identifier") String identifier,
//                                 @Query("transaction_category") String transaction_category,
//                                 @Query("tran_status") String tran_status);
//
//    @GET("{path}")
//    Call<DateClick> getevents(@Path("path") String path, @Query("phoneNo") String phoneNo, @Query("studentID") String studentID, @Query("date") String date);
//
//    @GET("{path}")
//    Call<LoginPHP> getlogin(@Path("path") String path, @Query("phoneNo") String phoneNo, @Query("password") String password, @Query("email") String email);
//
//    @GET("{path}")
//    Call<ExamlistDetails> getexam(@Path("path") String path, @Query("phoneNo") String phoneNo, @Query("studentID") String studentID, @Query("standardSubjectID") String standardSubjectID);
//
//
//    @GET("{path}")
//    Call<AttendanceDetailsList> getattendance(@Path("path") String path, @Query("phoneNo") String phoneNo, @Query("studentID") String studentID);
//
//    @GET("{path}")
//    Call<ResultDetailsFullData> getresult(@Path("path") String path, @Query("phoneNo") String phoneNo, @Query("studentID") String studentID, @Query("examID") String examID);
//
//    @GET("{path}")
//    Call<TimeLineDetail> gettimeline(@Path("path") String path, @Query("phoneNo") String phoneNo, @Query("studentID") String studentID, @Query("date") String date);
//
//    @GET("{path}")
//    Call<LibraryTimeLineDetails> getstoretimeline(@Path("path") String path, @Query("phoneNo") String phoneNo, @Query("studentID") String studentID, @Query("type") String type, @Query("id") String id);
//
//    @GET("{path}")
//    Call<StoreTimeLineDetails> getstore(@Path("path") String path, @Query("phoneNo") String phoneNo, @Query("studentID") String studentID, @Query("type") String type, @Query("id") String id);
//
//    @GET("{path}")
//    Call<FeeTimeLine> getfeetimeline(@Path("path") String path, @Query("phoneNo") String phoneNo, @Query("studentID") String studentID, @Query("type") String type, @Query("id") String id);
//
//    @GET("{path}")
//    Call<TeacherTimelineDetails> getteachertimeline(@Path("path") String path, @Query("phoneNo") String phoneNo, @Query("studentID") String studentID, @Query("type") String type, @Query("id") String id);
//
//    @GET("{path}")
//    Call<BusTimeLineDetails> getbustimeline(@Path("path") String path, @Query("phoneNo") String phoneNo, @Query("studentID") String studentID, @Query("type") String type, @Query("id") String id);
//
//    @GET("{path}")
//    Call<RechargeTimelineDetails> getrecharge(@Path("path") String path, @Query("phoneNo") String phoneNo, @Query("studentID") String studentID, @Query("type") String type, @Query("id") String id);
//
//    @GET("{path}")
//    Call<Trans> gettranscharg(@Path("path") String path, @Query("studentID") String studentID, @Query("transactionCategoryKey") String transactionCategoryKey, @Query("requestDevice") String requestDevice);
//
//
//    @GET("{path}")
//    Call<AttendanceTimeLinedetails> getattndancetimeline(@Path("path") String path, @Query("phoneNo") String phoneNo, @Query("studentID") String studentID, @Query("type") String type, @Query("id") String id);


}
