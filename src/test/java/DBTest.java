import com.toys.ACB.component.SqlSessionBuilder;
import com.toys.ACB.entity.Type;
import com.toys.ACB.mapper.TypeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.toys.ACB.component"})
@SpringBootTest(classes = DBTest.class)
public class DBTest {
    @Autowired
    private SqlSessionBuilder sqlSessionBuilder;

    @Test
    public void test1() {
        SqlSession sqlSession = sqlSessionBuilder.getSqlSession();
        TypeMapper typeMapper = sqlSession.getMapper(TypeMapper.class);

        Type type1 = new Type();
        type1.setName("type1");
        type1.setUserId(1L);
        typeMapper.insertSelective(type1);

        Type type2 = new Type();
        type2.setName("type2");
        type2.setKind(1);
        type2.setUserId(2L);
        typeMapper.insertSelective(type2);

        System.out.println(type1);
        System.out.println(type2);

        sqlSession.close();
    }
}
