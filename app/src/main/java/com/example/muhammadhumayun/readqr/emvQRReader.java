package com.example.muhammadhumayun.readqr;

import android.util.Log;

import java.util.HashMap;

public class emvQRReader {

    /*
    this hashmap is used to map each tag value we get from our QR string to the official defination of the tag from EMVCO
     */
   private HashMap<String, String> emvQrMap = new HashMap<String, String>();

    public    emvQRReader(){



        //00, 01 mandatory
        emvQrMap.put("00", "PAYLOAD FORMAT INDICATOR");
        emvQrMap.put("01", "POINT INITIATION METHOD");


    /*
        02 to 51 Merchant Account Information
         */
        emvQrMap.put("02", "MERCHANT IDENTIFIER VISA");
        emvQrMap.put("03", "MERCHANT IDENTIFIER VISA");
        emvQrMap.put("04", "MERCHANT IDENTIFIER MASTER CARD");
        emvQrMap.put("05", "MERCHANT IDENTIFIER MASTER CARD");
        emvQrMap.put("06", "MERCHANT IDENTIFIER EMVCo");
        emvQrMap.put("07", "MERCHANT IDENTIFIER EMVCo");
        emvQrMap.put("08", "MERCHANT IDENTIFIER EMVCo");
        emvQrMap.put("09", "MERCHANT IDENTIFIER Discover");
        emvQrMap.put("15", "MERCHANT IDENTIFIER UnionPay");
        emvQrMap.put("16", "MERCHANT IDENTIFIER UnionPay ");


        emvQrMap.put("26", " MERCHANT IDENTIFIER Pay Pak ");
        emvQrMap.put("27", " MERCHANT IDENTIFIER Pay Pak ");


        // continue till 51 are merchant tags


        //mandatory and / or conditional
        emvQrMap.put("52", "MERCHANT CATEGORY CODE");
        emvQrMap.put("53", "CURRENCY CODE");
        emvQrMap.put("54", "TRANSACTIONAL AMOUNT");


        emvQrMap.put("58", "COUNTRY CODE");
        emvQrMap.put("59", "MERCHANT NAME");
        emvQrMap.put("60", "MERCHANT CITY");
        emvQrMap.put("63", "CRC");
    }




    /*
    this method reads the EMVCO standard merchant QR
     */
    public void readEMVQR(String str){
        //Handling string result tag 00
        int lengthnum = 0;//initial length
        String tagm = ""; //tag map
        String value,TLV;// value and tag,vale,length combined
        /*
        while loop to get tag, length, value until we reach end of string
         */
        while (str.length() >0){
            String  tag = str.substring(0, 2);  //tag first two tag
            String  length = str.substring(2, 4); // length // next two length




            /*
            check the length characters and let the length var accordingly
             */
            if (length.equals("02")){
                lengthnum = 2;
            }
            else if(length.equals("03"))
            {
                lengthnum = 3;
            }
            else if (length.equals("04"))
            {
                lengthnum = 4;
            }
            else  if (length.equals("05"))
            {
                lengthnum=5;
            }
            else if(length.equals("06"))
            {
                lengthnum = 6;
            }
            else if (length.equals("07"))
            {
                lengthnum = 7;
            }
            else  if (length.equals("08"))
            {
                lengthnum=8;
            }
            else {

                lengthnum = Integer.parseInt(length);
            }

            value = str.substring(4, lengthnum+ 4); //value string

            try {
                tagm = emvQrMap.get(tag); //get the emv representation of tag
            }
            catch (Exception e){
                e.printStackTrace();
            }
            MainActivity.tvCardText.append(tag + "   indicates:  " + tagm + "\n        value is: " + value+ "\n\n"); //append to textview

            /*
            check first two chars of the value string and if they are any of the below call the function again
             */
            if(firstTwo(value).equals("00")){
                readEMVQR(value);
            }
            else if(firstTwo(value).equals("03"))
            { readEMVQR(value); }
            else if(firstTwo(value).equals("06"))
            { readEMVQR(value); }
            else if(firstTwo(value).equals("07"))
            { readEMVQR(value); }
            else if(firstTwo(value).equals("09"))
            { readEMVQR(value); }

            TLV = tag+length+value;//combine t + l + v
            str = str.substring(TLV.length(), str.length());//remove the constructed tlv from the main string //while loop condition handling
            Log.d("tag " , tag);
            //Log.d("tagmap " , tagm);
            Log.d("taglen", length);
            Log.d("tagval ", value);
        }


/*


        int startlenPFI = 0;
        tagPFI = resultString.substring(startlenPFI, 2);  //tag first two tag
        lengthPFI = resultString.substring(2, 4);
        int lenthofPFI = lengthPFI.length();
        String tagmapPFI = emvQrMap.get(tagPFI);
        String valuePFI = resultString.substring(4, lenthofPFI + 4);// is either 01 or 02 , type 1 qr or type 2 qr
        int vallenPFI = valuePFI.length() + tagPFI.length() + lengthPFI.length();


        //handling string result tag 01
        int startlenPIM= vallenPFI; // 2 BELONGS POINT INITIATION METHOD
        String tagPIM = resultString.substring(startlenPIM, startlenPIM + 2);
        String lenPIM = resultString.substring(startlenPIM + 2, startlenPIM + 4);
        int lenofPIM = lenPIM.length();
        String tagmapPIM = emvQrMap.get(tagPIM);
        String valPIM = resultString.substring(startlenPIM + 4, lenofPIM + startlenPIM + 4);// is either 12 dynamic QR , 11 static QR
        int vallenPIM = tagPIM.length() + lenPIM.length() + valPIM.length();





        //handling string result tag 04
        int startlen4 = startlenPIM + vallenPFI; // 2 BELONGS POINT INITIATION METHOD
        String tag4 = resultString.substring(startlen4, startlen4 + 2);
        String len4 = resultString.substring(startlen4 + 2, startlen4 + 4);
        int lenof4 = Integer.parseInt(len4);
        String tagmap4 = emvQrMap.get(tag4);
        String val4 = resultString.substring(startlen4 + 4, lenof4 + startlen4 + 4);// belongs to the merchant
        int vallen4 = tag4.length() + len4.length() + val4.length();
        merchantString = val4;



        if (firstTwo(merchantString).equals("00")) {


            //HANDLING MERHCANT CODE STRING PART 1


            //handling the merchant string part 00
            int mslen0 = 0;
            tagms0 = merchantString.substring(mslen0, mslen0 + 2);
            lenms0 = merchantString.substring(mslen0 + 2, mslen0 + 4);
            int lenofms0 = Integer.parseInt(lenms0);
            tagmapms0 = emvQrMap.get(tagms0);
            valms0 = merchantString.substring(mslen0 + 4, lenofms0 + mslen0 + 4);// handling part one of the merchant string
            int vallenms0 = tagms0.length() + lenms0.length() + valms0.length();

            //handling the merchant string part 01
            int mslen1 = vallenms0;
            tagms01 = merchantString.substring(mslen1, mslen1 + 2);
            lenms01 = merchantString.substring(mslen1 + 2, mslen1 + 4);
            int lenofms01 = Integer.parseInt(lenms01);
            tagmapms01 = emvQrMap.get(tagms01);
            valms01 = merchantString.substring(mslen1 + 4, lenofms01 + mslen1 + 4);// handling part two of merchant string
            int vallenms01 = tagms01.length() + tagms01.length() + valms01.length();

        }


        //handling starting result tag 05
        int startlen5 = startlen4 + vallen4; // BELONGS TO Merchant
        String tag5 = resultString.substring(startlen5, startlen5 + 2);
        String len5 = resultString.substring(startlen5 + 2, startlen5 + 4);
        int lenof5 = Integer.parseInt(len5)     */
        /*      *2 *//*
; //THE LENGTH OF VALUE IS 2 TIMES THE GIVEN LENGTH, ERROR IN THE 1LINK CODE
        String tagmap5 = emvQrMap.get(tag5);
        String val5 = resultString.substring(startlen5 + 4, lenof5 + startlen5 + 4);// merchant code string 2
        int vallen5 = tag5.length() + len5.length() + val5.length();
        // String view = tag3+val3+len3;
        merchantString2 = val5;



        //HANDLING MERHCANT CODE STRING PART 2
        if (firstTwo(merchantString2).equals("00")) {


            //handling the merchant string part 00
            int ms2len0 = 0;
            tagms20 = merchantString2.substring(ms2len0, ms2len0 + 2);
            lenms20 = merchantString2.substring(ms2len0 + 2, ms2len0 + 4);
            int lenofms20 = Integer.parseInt(lenms20);
            tagmapms20 = emvQrMap.get(tagms20);
            valms20 = merchantString2.substring(ms2len0 + 4, lenofms20 + ms2len0 + 4);// handling part one of the merchant string
            int vallenms20 = tagms20.length() + lenms20.length() + valms20.length();

            //handling the merchant string part 01
            int ms2len1 = vallenms20;
            tagms201 = merchantString2.substring(ms2len1, ms2len1 + 2);
            lenms201 = merchantString2.substring(ms2len1 + 2, ms2len1 + 4);
            int lenofms201 = Integer.parseInt(lenms201);
            tagmapms201 = emvQrMap.get(tagms201);
            valms201 = merchantString2.substring(ms2len1 + 4, lenofms201 + ms2len1 + 4);// handling part two of the merchant string
            int vallenms201 = lenms201.length() + tagms201.length() + valms201.length();


        }


        //handling starting result tag 06
        //change all the value onwards to 06..07..08..09
        int startlen6 = vallen5 + startlen5; // 4 BELONGS TO MASTERS
        String tag6 = resultString.substring(startlen6, startlen6 + 2);
        String len6 = resultString.substring(startlen6 + 2, startlen6 + 4);
        int lenof6 = Integer.parseInt(len6)  */
        /*  +2  *//*
; // THE LENGTH OF VALUE IS 2 CHAR LESS THEN THE ACTUAL LENGTH OF VALUE
        String tagmap6 = emvQrMap.get(tag6);
        String val6 = resultString.substring(startlen6 + 4, lenof6 + startlen6 + 4);// mercchant account number
        int vallen6 = tag6.length() + len6.length() + val6.length();


        //handling starting result tag 07
        int startlen7 = vallen6 + startlen6; // 4 BELONGS TO MASTERS
        String tag7 = resultString.substring(startlen7, startlen7 + 2);
        String len7 = resultString.substring(startlen7 + 2, startlen7 + 4);
        int lenof7 = Integer.parseInt(len7); // THE LENGTH OF VALUE IS 2 CHAR LESS THEN THE ACTUAL LENGTH OF VALUE
        String tagmap7 = emvQrMap.get(tag7);
        String val7 = resultString.substring(startlen7 + 4, lenof7 + startlen7 + 4);// mercchant account number
        int vallen7 = tag7.length() + len7.length() + val7.length();


        //handling starting result tag 08
        int startlen8 = vallen7 + startlen7; // 4 BELONGS TO MASTERS
        String tag8 = resultString.substring(startlen8, startlen8 + 2);
        String len8 = resultString.substring(startlen8 + 2, startlen8 + 4);
        int lenof8 = Integer.parseInt(len8); // THE LENGTH OF VALUE IS 2 CHAR LESS THEN THE ACTUAL LENGTH OF VALUE
        String tagmap8 = emvQrMap.get(tag8);
        String val8 = resultString.substring(startlen8 + 4, lenof8 + startlen8 + 4);// mercchant account number
        int vallen8 = tag8.length() + len8.length() + val8.length();


        //handling tag 09
        int startlen9 = vallen8 + startlen8; // 4 BELONGS TO MASTERS
        String tag9 = resultString.substring(startlen9, startlen9 + 2);
        String len9 = resultString.substring(startlen9 + 2, startlen9 + 4);
        int lenof9 = Integer.parseInt(len9); // THE LENGTH OF VALUE IS 2 CHAR LESS THEN THE ACTUAL LENGTH OF VALUE
        String tagmap9 = emvQrMap.get(tag9);
        String val9 = resultString.substring(startlen9 + 4, lenof9 + startlen9 + 4);// mercchant account number
        int vallen9 = tag9.length() + len9.length() + val9.length();


        //REMOVE THE  *2 AND +2 AND ADD 76 BELOW TO MAKE IT M=WORK WITH OTHER CODE


        //lets now skip the merchant tags and go directly to handle 52

        int startlen52 = startlen5 + vallen5; // 52 IS MCC // 76 chars from string skipped to reach 52
        String tag52 = resultString.substring(startlen52, startlen52 + 2);
        String len52 = resultString.substring(startlen52 + 2, startlen52 + 4);
        int lenof52 = Integer.parseInt(len52);
        String tagmap52 = emvQrMap.get(tag52);
        String val52 = resultString.substring(startlen52 + 4, lenof52 + startlen52 + 4);// amount
        int vallen52 = tag52.length() + len52.length() + val52.length();


        //handling 53
        int startlen53 = startlen52 + vallen52; // 53 IS CURRENCY CODE
        String tag53 = resultString.substring(startlen53, startlen53 + 2);
        String len53 = resultString.substring(startlen53 + 2, startlen53 + 4);
        int lenof53 = Integer.parseInt(len53);
        String tagmap53 = emvQrMap.get(tag53);
        String val53 = resultString.substring(startlen53 + 4, lenof53 + startlen53 + 4);// currency code
        int vallen53 = tag53.length() + len53.length() + val53.length();

        //HANDLING 54
        int startlen54 = startlen53 + vallen53; // 54 IS  TRANSACTION AMOUNT
        String tag54 = resultString.substring(startlen54, startlen54 + 2);
        String len54 = resultString.substring(startlen54 + 2, startlen54 + 4);
        int lenof54 = Integer.parseInt(len54);
        String tagmap54 = emvQrMap.get(tag54);
        String val54 = resultString.substring(startlen54 + 4, lenof54 + startlen54 + 4);// amount
        int vallen54 = tag54.length() + len54.length() + val54.length();


        //HANDLING 58 , IN OUR TEST EMV QR IS COMES AFTER 54

        int startlen58 = startlen54 + vallen54; // 58 IS COUNTRY CODE
        String tag58 = resultString.substring(startlen58, startlen58 + 2);
        String len58 = resultString.substring(startlen58 + 2, startlen58 + 4);
        int lenof58 = Integer.parseInt(len58);
        String tagmap58 = emvQrMap.get(tag58);
        String val58 = resultString.substring(startlen58 + 4, lenof58 + startlen58 + 4);// COUNTRY CODE
        int vallen58 = tag58.length() + len58.length() + val58.length();


        //HANDLING 59 , IN OUR TEST EMVQR IS COMES AFTER 58

        int startlen59 = startlen58 + vallen58; // 59 IS MERCHANT NAME
        String tag59 = resultString.substring(startlen59, startlen59 + 2);
        String len59 = resultString.substring(startlen59 + 2, startlen59 + 4);
        int lenof59 = Integer.parseInt(len59);
        String tagmap59 = emvQrMap.get(tag59);
        String val59 = resultString.substring(startlen59 + 4, lenof59 + startlen59 + 4);// MERCHANT NAME
        int vallen59 = tag59.length() + len59.length() + val59.length();


        //HANDLING 60 , IN OUR TEST EMVQR IS COMES AFTER 59

        int startlen60 = startlen59 + vallen59; // 60 IS MERCHANT CITY
        String tag60 = resultString.substring(startlen60, startlen60 + 2);
        String len60 = resultString.substring(startlen60 + 2, startlen60 + 4);
        int lenof60 = Integer.parseInt(len60);
        String tagmap60 = emvQrMap.get(tag60);
        String val60 = resultString.substring(startlen60 + 4, lenof60 + startlen60 + 4);// MERCHANT CITY
        int vallen60 = tag60.length() + len60.length() + val60.length();


        //HANDLING 63 WHICH COMES AFTER 60 IN OUR TEST EMVQR

        int startlen63 = startlen60 + vallen60; // 60 IS MERCHANT CITY
        String tag63 = resultString.substring(startlen63, startlen63 + 2);
        String len63 = resultString.substring(startlen63 + 2, startlen63 + 4);
        int lenof63 = Integer.parseInt(len63);
        String tagmap63 = emvQrMap.get(tag63);
        String val63 = resultString.substring(startlen63 + 4, lenof63 + startlen63 + 4);// MERCHANT CITY
        int vallen63 = tag63.length() + len63.length() + val63.length();


        MainActivity.tvCardText.setText(tagPFI + "\nindicates:     " + tagmapPFI + "\nvalue is " + valuePFI+ "\n" +

                tagPIM + "\n indicates    " + tagmapPIM + "\nvalue is : " + valPIM
                //  + "\n" + tag3 +"\n indicates  "+ tagmap3 + "\nvalue is : "+val3
                + "\n" + tag4 + "\n indicates    " + tagmap4 + "\n value is : " + val4

                + "\n\nInternal merchant string"
                + "\n" + tagms0 + "\n indicates    " + tagmapms0 + "\n value is : " + valms0
                + "\n" + tagms01 + "\n indicates    " + tagmapms01 + "\n value is : " + valms01

                + "\n" + tag5 + "\n indicates    " + tagmap5 + "\n value is : " + val5

                + "\n\nInternal merchant string"
                + "\n" + tagms20 + "\n indicates    " + tagmapms20 + "\n value is : " + valms20
                + "\n" + tagms201 + "\n indicates    " + tagmapms201 + "\n value is : " + valms201



                + "\n" + tag6 + "\n indicates    " + tagmap6 + "\n value is : " + val6
                + "\n" + tag7 + "\n indicates    " + tagmap7 + "\n value is : " + val7
                + "\n" + tag8 + "\n indicates    " + tagmap8 + "\n value is : " + val8
                + "\n" + tag9 + "\n indicates    " + tagmap9 + "\n value is : " + val9


                + "\n" + tag52 + "\n indicates  " + tagmap52 + "\n value is : " + val52
                + "\n" + tag53 + "\nindicates  " + tagmap53 + "\n value is : " + val53
                + "\n" + tag54 + "\nindicates  " + tagmap54 + "\n value is : " + val54
                + "\n" + tag58 + "\nindicates  " + tagmap58 + "\n value is : " + val58
                + "\n" + tag59 + "\nindicates  " + tagmap59 + "\n value is : " + val59
                + "\n" + tag60 + "\nindicates  " + tagmap60 + "\n value is : " + val60
                + "\n" + tag63 + "\nindicates  " + tagmap63 + "\n value is : " + val63

        );
*/

    }


    /*
    this method returns the first two characters of any string
     */
    public String firstTwo(String str) {
        return str.length() < 2 ? str : str.substring(0, 2);
    }



}
