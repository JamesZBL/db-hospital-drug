package me.zbl.activity.service;

import java.util.Map;

public interface ProcessStartService {

    String startProcess(String procDefKey, String businessTable, String businessId, String title, Map<String, Object> vars);

}
