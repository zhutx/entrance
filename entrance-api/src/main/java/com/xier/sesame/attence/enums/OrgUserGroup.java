package com.xier.sesame.attence.enums;

import java.util.*;

/**
 * Created by ybwu on 2016/8/27.
 */
public class OrgUserGroup {
    /*借还者*/
    public final static int ORG_BORROW_RETURN = 3;/** 借还者  **/
    /*前台*/
    public final static int ORG_PROSCENIUM = 6;/** 前台  **/
    /** 机构工作人员 **/
    public final static int ORG_COMMUNITY_MATERIAL_MANAGEMENT = 1103; /** 物管员 */
    public final static int ORG_COMMUNITY_CLEANER = 1104;/** 保洁员 */
    public final static int ORG_COMMUNITY_SERVICE = 1105;/** 维修员 */
    public final static int ORG_COMMUNITY_SECURITY = 1106;/** 社区保安员 */
    /** 机构人员  **/
    public final static int ORG_USER = 97;/** 机构人员  **/
    public final static int ORG_USER_OWNER = 1;/** 业主 */
    public final static int ORG_USER_TENANT = 2;/** 租户 例:租客*/
    public final static int ORG_USER_COMM_FRIENDS = 1107;/** 亲友 */
    public final static int ORG_USER_COMM_FAMILY = 1108;/** 家属 */
    /** 访客  **/
    public final static int VISITOR = 503;
    public final static int LONG_VISITOR = 4;
    public final static int SHORT_VISITOR = 5;

    private final static HashMap<Integer,String> ORG_USER_WORK_MAP= new LinkedHashMap<>();
    static{

        ORG_USER_WORK_MAP.put(ORG_COMMUNITY_MATERIAL_MANAGEMENT,"物管员");
        ORG_USER_WORK_MAP.put(ORG_COMMUNITY_CLEANER,"保洁员");
        ORG_USER_WORK_MAP.put(ORG_COMMUNITY_SERVICE,"维修员");
        ORG_USER_WORK_MAP.put(ORG_COMMUNITY_SECURITY,"社区保安员");
    }

    public static HashMap<Integer, String> getOrgWorkMap() {
        return ORG_USER_WORK_MAP;
    }
    private final static List<Integer> ORG_USER_WORK_LIST = new ArrayList<>();
    static {
        ORG_USER_WORK_LIST.add(ORG_COMMUNITY_MATERIAL_MANAGEMENT);
        ORG_USER_WORK_LIST.add(ORG_COMMUNITY_CLEANER);
        ORG_USER_WORK_LIST.add(ORG_COMMUNITY_SERVICE);
        ORG_USER_WORK_LIST.add(ORG_COMMUNITY_SECURITY);
    }
    public static List<Integer> getOrgUserWorkList(){
        return ORG_USER_WORK_LIST;
    }

    private final static HashMap<Integer,String> ORG_USER_COMMUNITY_MAP= new LinkedHashMap<>();
    static{

        ORG_USER_COMMUNITY_MAP.put(ORG_USER_OWNER,"业主");
        ORG_USER_COMMUNITY_MAP.put(ORG_USER_TENANT,"租户");
        ORG_USER_COMMUNITY_MAP.put(ORG_USER_COMM_FAMILY,"家属");
        ORG_USER_COMMUNITY_MAP.put(ORG_USER_COMM_FRIENDS,"亲友");

    }

    public static HashMap<Integer, String> getOrgCommunityMap() {
        return ORG_USER_COMMUNITY_MAP;
    }

    private final static List<Integer> ORG_USER_COMMUNITY_LIST = new ArrayList<>();
    static {
        ORG_USER_COMMUNITY_LIST.add(ORG_USER_OWNER);
        ORG_USER_COMMUNITY_LIST.add(ORG_USER_TENANT);
        ORG_USER_COMMUNITY_LIST.add(ORG_USER_COMM_FAMILY);
        ORG_USER_COMMUNITY_LIST.add(ORG_USER_COMM_FRIENDS);

    }
    public static List<Integer> getOrgUserCommunityList(){
        return ORG_USER_COMMUNITY_LIST;
    }

    private final static HashMap<Integer,String> ORG_USER_MAP= new LinkedHashMap<>();
    static {
        ORG_USER_MAP.put(ORG_USER,"机构人员");
        ORG_USER_MAP.put(ORG_PROSCENIUM,"前台");
        ORG_USER_MAP.put(ORG_COMMUNITY_MATERIAL_MANAGEMENT,"物管员");
        ORG_USER_MAP.put(ORG_COMMUNITY_CLEANER,"保洁员");
        ORG_USER_MAP.put(ORG_COMMUNITY_SERVICE,"维修员");
        ORG_USER_MAP.put(ORG_COMMUNITY_SECURITY,"社区保安员");
        ORG_USER_MAP.put(ORG_USER_OWNER,"业主");
        ORG_USER_MAP.put(ORG_USER_TENANT,"租户");
        ORG_USER_MAP.put(ORG_USER_COMM_FAMILY,"家属");
        ORG_USER_MAP.put(ORG_USER_COMM_FRIENDS,"亲友");
    }

    public static HashMap<Integer, String> getOrgUserMap(){

        return ORG_USER_MAP;
    }

    public static boolean isOrgUser(Integer i){
        if(i!=null && ORG_USER_MAP.containsKey(i)){
            return true;
        }

        return false;
    }
    public static boolean isNotOrgUser(Integer i){

        return !isOrgUser(i);
    }
    public  static String getOrgUserStr(Integer i){
        if(i!=null && ORG_USER_MAP.containsKey(i)){
            return ORG_USER_MAP.get(i);
        }
        return "";
    }

    private final static List<Integer> ORG_USER_LIST = new ArrayList<>();
    static {
        ORG_USER_LIST.add(ORG_USER);
        ORG_USER_LIST.add(ORG_PROSCENIUM);
        ORG_USER_LIST.add(ORG_COMMUNITY_MATERIAL_MANAGEMENT);
        ORG_USER_LIST.add(ORG_COMMUNITY_CLEANER);
        ORG_USER_LIST.add(ORG_COMMUNITY_SERVICE);
        ORG_USER_LIST.add(ORG_COMMUNITY_SECURITY);
        ORG_USER_LIST.add(ORG_USER_OWNER);
        ORG_USER_LIST.add(ORG_USER_TENANT);
        ORG_USER_LIST.add(ORG_USER_COMM_FRIENDS);
        ORG_USER_LIST.add(ORG_USER_COMM_FAMILY);
    }
    public static List<Integer> getOrgUserList(){
        return ORG_USER_LIST;
    }

    public static boolean isOrgWorkUser(Integer i){
        if(i!=null && ORG_USER_WORK_MAP.containsKey(i)){
            return true;
        }

        return false;
    }

    public static boolean isNotOrgWorkUser(Integer i){

        return !isOrgWorkUser(i);
    }

    public static boolean isOrgCommunityUser(Integer i){
        if(i!=null && ORG_USER_COMMUNITY_MAP.containsKey(i)){
            return true;
        }

        return false;
    }

    public static boolean isNotOrgCommunityUser(Integer i){

        return !isOrgCommunityUser(i);
    }

    private final static Map<Integer, String> VISITOR_MAP = new HashMap<>();
    static {
        VISITOR_MAP.put(VISITOR,"访客");
        VISITOR_MAP.put(LONG_VISITOR,"长期访客");
        VISITOR_MAP.put(SHORT_VISITOR,"短期访客");
    }

    public static Map<Integer,String> getVisitorMap(){
        return VISITOR_MAP;
    }

    public static boolean isVisitor(Integer i){
        if(i!=null&&VISITOR_MAP.containsKey(i)){
            return true;
        }
        return false;
    }

    private final static List<Integer> VISITOR_LIST = new ArrayList<>();
    static {
        VISITOR_LIST.add(VISITOR);
        VISITOR_LIST.add(LONG_VISITOR);
        VISITOR_LIST.add(SHORT_VISITOR);
    }
    public static List<Integer> getOrgVisitorList(){
        return VISITOR_LIST;
    }


}
