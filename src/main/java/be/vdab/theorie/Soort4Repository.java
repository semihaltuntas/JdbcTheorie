package be.vdab.theorie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Soort4Repository extends AbstractRepository {

    long create(String naam) throws SQLException {
        String sql = """
                insert into soorten (naam)
                values (?)
                """;
        try (Connection connection = super.getConnection();
             PreparedStatement statementInsert = connection.prepareStatement(sql,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            statementInsert.setString(1, naam);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            /*
            * TRANSACTION_READ_COMMITTED: Bu izolasyon seviyesinde, bir işlem yürütüldüğünde, diğer işlemler tarafından yapılan değişiklikler hemen görünür olmaz.
            * Yani, bir işlem tarafından yapılan değişiklikler, yalnızca bu işlem tarafından gerçekleştirilen bir COMMIT işleminden sonra diğer işlemler tarafından görünür hale gelir.
            * Bu izolasyon seviyesi, bir işlem başka bir işlemin yaptığı değişiklikleri sadece işlem tamamlandıktan sonra görmesini sağlar.*/
            connection.setAutoCommit(false);
            statementInsert.executeUpdate();
            ResultSet result = statementInsert.getGeneratedKeys();
            result.next();
            long nieuweId = result.getLong(1);
            connection.commit();
            return nieuweId;
        }
        /*
        * String sql = """ ... """;: Bu satır, SQL sorgusunu bir Java String değişkenine atar. Bu SQL sorgusu, soorten tablosuna bir naam değeri eklemek için kullanılır. Yer tutucusu olarak ? sembolü kullanılmıştır.

try (Connection connection = super.getConnection(); ... ): Bu satır, try-with-resources bloğunu başlatır. Bu blok, otomatik olarak Connection nesnesini kapatır ve kaynakları temizler. super.getConnection() metodu, AbstractRepository sınıfından miras alınan getConnection() metodunu çağırarak bir bağlantı alır.

PreparedStatement statementInsert = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS): Bu satır, bağlantı üzerinden bir PreparedStatement nesnesi oluşturur. PreparedStatement.RETURN_GENERATED_KEYS işareti, oluşturulan anahtarları (genellikle otomatik artan anahtarlar) döndürmek için kullanılır.

statementInsert.setString(1, naam);: Bu satır, PreparedStatement nesnesinin birinci parametresine (yer tutucusuna) naam değerini atar. Bu, SQL sorgusunda belirtilen ? yer tutucusuna karşılık gelir.

connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);: Bu satır, bağlantının izolasyon seviyesini TRANSACTION_READ_COMMITTED olarak ayarlar. Bu, aynı anda yürütülen başka transaksiyonlardan gelebilecek verilere nasıl erişileceğini belirler.

connection.setAutoCommit(false);: Bu satır, otomatik işlem modunu kapatır. Bu, işlemi manuel olarak kontrol etmemizi sağlar.

statementInsert.executeUpdate();: Bu satır, PreparedStatement ile belirtilen SQL sorgusunu yürütür. Bu durumda, yeni bir kayıt eklemek için executeUpdate() kullanılır.

ResultSet result = statementInsert.getGeneratedKeys();: Bu satır, oluşturulan anahtarları almak için bir ResultSet nesnesi alır.

result.next();: Bu satır, ResultSet'in bir sonraki satıra ilerlemesini sağlar.

long nieuweId = result.getLong(1);: Bu satır, ResultSet'ten oluşturulan anahtarı alır ve nieuweId değişkenine atar.

connection.commit();: Bu satır, işlemi başarıyla tamamladığımızı ve değişiklikleri kalıcı hale getirmek için işlemi onaylar (commit).

return nieuweId;: Bu satır, yeni eklenen kaydın kimlik değerini döndürür.

Bu kod, veritabanına yeni bir kayıt eklemek için kullanılır ve eklenen kaydın kimlik değerini geri döndürür. Aynı zamanda, bu işlemi bir transaksiyon içinde gerçekleştirir ve otomatik olarak oluşturulan anahtarları almak için PreparedStatement.RETURN_GENERATED_KEYS kullanır.*/


    }
}
