package com.javarestassuredtemplate.dbsteps;

import com.javarestassuredtemplate.utils.DBUtils;
import com.javarestassuredtemplate.utils.GeneralUtils;

public class UsuariosDBSteps {
    private static String queriesPath = System.getProperty("user.dir")+"/src/test/java/com/javarestassuredtemplate/queries/";

    public static String retornaSenhaDoUsuario(String userName){
        String query = GeneralUtils.readFileToAString(queriesPath + "retornaSenhaUsuarioQuery");
        query.replace("$nome", userName);

        return DBUtils.getQueryResult(query).get(0);
    }
}
