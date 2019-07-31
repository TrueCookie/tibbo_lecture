package com.tibbo.aggregate;

import com.tibbo.aggregate.common.Log;
import com.tibbo.aggregate.common.context.Context;
import com.tibbo.aggregate.common.context.ContextManager;
import com.tibbo.aggregate.common.datatable.DataTable;
import com.tibbo.aggregate.common.protocol.RemoteServer;
import com.tibbo.aggregate.common.protocol.RemoteServerController;

public class GetServerVersion {
    public GetServerVersion() {
    }

    public static void main(String[] args) {
        try {
            Log.start();
            RemoteServer rls = new RemoteServer("104.248.243.143", 6460, "admin", "admin123");
            RemoteServerController rlc = new RemoteServerController(rls, true);
            rlc.connect();
            rlc.login();
            ContextManager cm = rlc.getContextManager();
            Context rootContext = cm.getRoot();
            DataTable versionData = rootContext.getVariable("version");
            String serverVersion = versionData.rec().getString("version");
            System.out.println("Server version: " + serverVersion);
            rlc.disconnect();
        } catch (Exception var7) {
            var7.printStackTrace();
        }

    }
}
