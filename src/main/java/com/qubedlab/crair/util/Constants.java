package com.qubedlab.crair.util;

public class Constants {

    public static final String genResponseTopic = "gen-response";
    public static final String BRANCHID = "branchid";
    public static final String BRANCHID_TEMP = "1509931";
    public static final String PARENTID_TEMP = "1509932";
    public static final String PARENTID = "parentid";
    public static final String firstName = "firstName";
    public static final String LastName = "Last_Name";
    public static final String Mailing_Postal_Code = "Mailing_Postal_Code";
    public static final String Date_of_Birth = "Date_of_Birth";
    public static final String First_Name = "First_Name";
    public static final String Mailing_Jurisdiction_Code = "Mailing_Jurisdiction_Code";
    public static final String Sex = "Sex";
    public static final String License_ID_Number = "License_ID_Number";
    public static final String Height_in_FT_IN = "Height_in_FT_IN";
    public static final String Eye_Color = "Eye_Color";
    public static final String CUSTOMER_GLOBAL_ID = "customerGlobalID";

    public static final String SEARCHVALUE = "searchvalue";
    public static final String USER_ID = "userId";
    public static final String MANAGER = "manager";
    public static final String USER_ROLE = "userRole";

    public static final String IDDATA = "idData";

    public static final String EMPTY_STRING = "";
    public static final String SPACE = " ";
    public static final String TAB = "\t";
    public static final String SINGLE_QUOTE = "'";
    public static final String PERIOD = ".";
    public static final String DOUBLE_QUOTE = "\"";
    public static final String INVENTORY ="https://qubedlab-dealer-inventory.herokuapp.com/extract";
    // PRIVATE //

    /**
     * The caller references the constants using <tt>Consts.EMPTY_STRING</tt>, and
     * so on. Thus, the caller should be prevented from constructing objects of this
     * class, by declaring this private constructor.
     */
    private Constants() {
	// this prevents even the native class from
	// calling this ctor as well :
	throw new AssertionError();
    }

}
