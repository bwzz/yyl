package pl.surecase.eu;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyDaoGenerator {

    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(3, "com.yuantiku.dbdata");
        addAccount(schema);
        new DaoGenerator().generateAll(schema, args[0]);
    }

    private static void addAccount(Schema schema) {
        Entity account = schema.addEntity("Account");
        account.implementsSerializable();
        account.addStringProperty("name").notNull();
        account.addStringProperty("ldap").notNull().primaryKey();
        account.addStringProperty("email");
        account.addStringProperty("phone");
        account.addStringProperty("dept");
        account.addStringProperty("googleAccount");
        account.addStringProperty("birth");
        account.addStringProperty("constellation");
    }
}
