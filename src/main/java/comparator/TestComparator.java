package comparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestComparator {
    public static void main(String[] args) {
        String str2 = "str1\n" +
                "str2\n" +
                "str3\n" +
                "str4\n" +
                "str5\n" +
                "str6\n" +
                "str7\n" +
                "str8\n" +
                "str9\n" +
                "str10\n" +
                "str11\n" +
                "str12\n" +
                "str13\n" +
                "str14\n" +
                "str15\n" +
                "str16\n" +
                "str17\n" +
                "str18\n" +
                "str19\n" +
                "str20\n"
                ;
        String str1 = "str1\n" +
                "str2\n" +
                "str3\n" +
                "str4\n" +
                "str2\n" +
                "str6\n" +
                "str7\n" +
                "str8\n" +
                "str81\n" +
                "str82\n" +
                "str83\n" +
                "str84\n" +
                "str8fjhj5\n" +
                "str86\n" +
                "str81\n" +
                "str82\n" +
                "str83\n" +
                "str84\n" +
                "str85\n" +
                "str86\n" +
                "str9\n" +
                "str10\n" +
                "str11\n" +
                "str12\n" +
                "str13\n" +
                "str14\n" +
                "str155\n" +

                "str17\n" +
                "str16\n" +
                "str19\n" +
                "str20\n" +
                "str21\n" +
                "str219\n" +
                "str217\n" +
                "str316\n" +
                "str319\n" +
                "str420\n"
                ;

/*
        String str1 = "<scenario>\n" +
                "         <xbrldi:explicitMember dimension=\"es_dim:CT\">es_CT:x5</xbrldi:explicitMember>\n" +
                "         <xbrldi:explicitMember dimension=\"es_dim:LA\">es_GA:x55</xbrldi:explicitMember>\n" +
                "         <xbrldi:explicitMember dimension=\"es_dim:MC\">es_MC:x149</xbrldi:explicitMember>\n" +
                "         <xbrldi:explicitMember dimension=\"es_dim:MCG\">es_MC:x23</xbrldi:explicitMember>\n" +
                "         <xbrldi:explicitMember dimension=\"es_dim:PH\">es_PH:x7</xbrldi:explicitMember>\n" +
                "         <xbrldi:explicitMember dimension=\"es_dim:PRV\">es_GA:x53</xbrldi:explicitMember>\n" +
                "      </scenario>";
        String str2 = "<scenario>\n" +
                "         <xbrldi:explicitMember dimension=\"es_dim:CT\">es_CT:x5</xbrldi:explicitMember>\n" +
                "added string\n" +
                "         <xbrldi:explicitMember dimension=\"es_dim:LA\">es_GA:x55</xbrldi:explicitMember>\n" +
                "         <xbrldi:explicitMember dimension=\"es_dim:MC\">es_MC:x149</xbrldi:explicitMember>\n" +

                "         <xbrldi:explicitMember dimension=\"es_dim:PH\">es_PH:x7</xbrldi:explicitMember>\n" +
                "         <xbrldi:expghjlicitMember dimension=\"es_dim:PH\">es_PH:x7</xbrldi:explicitMember>\n" +
                "         <xbrldi:ex45licitMember dimension=\"es_dim:PH\">es_PH:x7</xbrldi:explicitMember>\n" +



                "         <xbrldi:LLexplicitMember dimension=\"es_dim:MCG\">es_MC:x23</xbrldi:explicitMember>\n" +
                "      </scenario>";
*/

        String[] strArray1 = str1.split("\n");
        String[] strArray2 = str2.split("\n");

        List<String> strListOld = Arrays.asList(strArray1);
        List<String> strListNew = Arrays.asList(strArray2);

        System.out.println(compareStringBySymbol("qstring", "stringtt"));

/*
        if(compareRows(strListOld, strListNew)) {
            System.out.println("Files are equals");
        } else {
            System.out.println("Files are different");
        }
*/

    }

    public static boolean compareRows(List<String> strListOld, List<String> strListNew) {
        boolean comparingResult = true;
        if(strListOld.size() != strListNew.size()) {
            comparingResult = false;
            System.out.println("strListOld.size() not equal strListNew.size(): strListOld.size() = " + strListOld.size() +
                    " , but strListNew.size() = " + strListNew.size());
        }

       // int depth = 5;
       // int maxSize = strListOld.size() > strListNew.size() ? strListOld.size() : strListNew.size();
        ArrayList<CompareResult> resultList = new ArrayList();

        int currentOld = 0;
        int currentNew = 0;
        int row = 1;

        while(true) {   //row
            if(currentOld < strListOld.size() && currentNew < strListNew.size()) {
//
                if (!strListOld.get(currentOld).equals(strListNew.get(currentNew))) {


                    for (int j = 1; (currentNew + j) < strListNew.size(); j++) {         // for (int j = 1; j <= depth && (currentNew + j) < strListNew.size(); j++) {
                        if (strListOld.get(currentOld).equals(strListNew.get(currentNew + j))) {
                            for (int i = 0; i < j; i++) {
                                resultList.add(new CompareResult(row++, "", strListNew.get(currentNew++), Status.ADDED));
                            }
                            resultList.add(new CompareResult(row++, strListOld.get(currentOld++), strListNew.get(currentNew++), Status.EQUAL));
                            break;

                        } else if (/*j == depth || */(currentNew + j) == strListNew.size() - 1) {
                            resultList.add(new CompareResult(row++, strListOld.get(currentOld++), "", Status.DELETED));
                            break;
                        }
                    }

                } else {
                    resultList.add(new CompareResult(row++, strListOld.get(currentOld++), strListNew.get(currentNew++), Status.EQUAL));
                }
            } else  if (currentNew < strListNew.size()) {
                int numberAdded = strListNew.size() - currentNew;
                for (int i = 0; i < numberAdded; i++) {
                    resultList.add(new CompareResult(row++, "", strListNew.get(currentNew++), Status.ADDED));
                }
            } else  if (currentOld < strListOld.size()) {
                int numberDeleted = strListOld.size() - currentOld;
                for (int i = 0; i < numberDeleted; i++) {
                    resultList.add(new CompareResult(row++, strListOld.get(currentOld++), "", Status.DELETED));
                }
            } else {
                break;

               // System.out.println("Fault");

            }

//


        } //while

        for (int i = 0; i < resultList.size(); i++) {
            if(true) {          //!resultList.get(i).status.equals(Status.EQUAL)
                System.out.println("row = " + resultList.get(i).row + ", " + "strOld = " + resultList.get(i).str1 + ", " +
                        "strNew = " +   resultList.get(i).str2 + ", " + "status = " + resultList.get(i).status );
            }

        }



        return comparingResult;
    }

    public static boolean compareStringBySymbol(String stringOld, String stringNew) {
        boolean comparingStringResult = true;
        if(stringOld.length() != stringNew.length()) {
            comparingStringResult = false;
            System.out.println("stringOld.length() not equal stringNew.length(): stringOld.length() = " + stringOld.length() +
                    " , but stringNew.length() = " + stringNew.length());
        }
        ArrayList<CompareStringResult> resultListString = new ArrayList();

        int currentOld = 0;
        int currentNew = 0;
        int number = 1;
        while(true) {   //row
            if(currentOld < stringOld.length() && currentNew < stringNew.length()) {
//
                if (stringOld.charAt(currentOld) != stringNew.charAt(currentNew)) {

                    for (int j = 1; (currentNew + j) < stringNew.length(); j++) {         // for (int j = 1; j <= depth && (currentNew + j) < strListNew.size(); j++) {
                        if (stringOld.charAt(currentOld) ==(stringNew.charAt(currentNew + j))) {
                            for (int i = 0; i < j; i++) {
                                resultListString.add(new CompareStringResult(number++, ' ', stringNew.charAt(currentNew++), Status.ADDED));
                            }
                            resultListString.add(new CompareStringResult(number++, stringOld.charAt(currentOld++), stringNew.charAt(currentNew++), Status.EQUAL));
                            break;

                        } else if (/*j == depth || */(currentNew + j) == stringNew.length() - 1) {
                            resultListString.add(new CompareStringResult(number++, stringOld.charAt(currentOld++), ' ', Status.DELETED));
                            break;
                        }
                    }

                } else {
                    resultListString.add(new CompareStringResult(number++, stringOld.charAt(currentOld++), stringNew.charAt(currentNew++), Status.EQUAL));
                }
            } else  if (currentNew < stringNew.length()) {
                int numberAdded = stringNew.length() - currentNew;
                for (int i = 0; i < numberAdded; i++) {
                    resultListString.add(new CompareStringResult(number++, ' ', stringNew.charAt(currentNew++), Status.ADDED));
                }
            } else  if (currentOld < stringOld.length()) {
                int numberDeleted = stringOld.length() - currentOld;
                for (int i = 0; i < numberDeleted; i++) {
                    resultListString.add(new CompareStringResult(number++, stringOld.charAt(currentOld++), ' ', Status.DELETED));
                }
            } else {
                break;

                // System.out.println("Fault");

            }

//


        } //while

        for (int i = 0; i < resultListString.size(); i++) {
            if(true) {          //!resultList.get(i).status.equals(Status.EQUAL)
                System.out.println("row = " + resultListString.get(i).number + ", " + "strOld = " + resultListString.get(i).ch1 + ", " +
                        "strNew = " +   resultListString.get(i).ch2 + ", " + "status = " + resultListString.get(i).status );
            }

        }

        return comparingStringResult;
    }

    private static class CompareResult {
        int row;
        String str1;
        String str2;
        Status status;

        public CompareResult(int row, String str1, String str2, Status status) {
            this.row = row;
            this.str1 = str1;
            this.str2 = str2;
            this.status = status;
        }
    }

    private static class CompareStringResult {
        int number;
        char ch1;
        char ch2;
        Status status;

        public CompareStringResult(int number, char ch1, char ch2, Status status) {
            this.number = number;
            this.ch1 = ch1;
            this.ch2 = ch2;
            this.status = status;
        }
    }


    enum Status {
        ADDED,
        DELETED,
        EQUAL
    }
}
