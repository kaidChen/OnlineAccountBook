import com.toys.ACB.Util.DBUtils;
import com.toys.ACB.dto.BillDetail;
import com.toys.ACB.entity.Type;
import com.toys.ACB.mapper.BillDetailMapper;
import com.toys.ACB.mapper.TypeMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.springframework.boot.test.context.SpringBootTest;

import static com.toys.ACB.mapper.BillDynamicSqlSupport.bill;
import static com.toys.ACB.mapper.TypeDynamicSqlSupport.type;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

@SpringBootTest(classes = DBTest.class)
public class DBTest {

}
