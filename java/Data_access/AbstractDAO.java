package Data_access;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Connection.ConnectionFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/***
 *In aceasta clasa se implementeaza interogarile si operatiile pe tabele:
 * Insert, FindAll, Update, Delete si FindByID
 * De asemenea exista si o functie care creaza tabelul
 * @param <T> T poate fi tipul Client, Product sau Order
 * @author Ioneasa Cristina
 */
public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    /***
     * creaza intergarea pentru
     * @param field - field pentru clauza where din interogare
     * @return interogarea (sub forma de string)
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM magazin.");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /***
     * Gaseste obiectul t cu id-ul id trimis ca parametru
     * @param id
     * @return t
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /***
     * Gaseste toate listele de obiecte (Client/Product/Order)
     * @return list
     */
    public List<T> findAll() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM magazin.");
        sb.append(type.getSimpleName().toLowerCase());
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = sb.toString();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /***
     * Creaza o lista de obiecte
     * @param resultSet -rezultatul returnat de interogare
     * @return lista de obicte
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;

        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /***
     * Creaza interogarea pentru insert
     * @param t
     * @return string
     */
    public String insertQuery(T t) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO magazin.");
        sb.append(type.getSimpleName());
        sb.append("(");

        String prefix = "";
        for (Field field : type.getDeclaredFields()) {
            String fieldName = field.getName();
            sb.append(prefix);
            prefix = ",";
            sb.append(fieldName);
        }
        sb.append(") \nVALUES (");

        prefix = "";
        for (Field field : type.getDeclaredFields()) {
            Object value;
            field.setAccessible(true);
            try {
                value = field.get(t);
                sb.append(prefix);
                prefix = ",";
                if(value instanceof String)
                    sb.append("'");
                sb.append(value.toString());
                if(value instanceof String)
                    sb.append("'");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        sb.append(");");

        return sb.toString();
    }

    /***
     * Insereaza obiectul t
     * @param t
     * @return t
     */
    public T insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = insertQuery(t);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();

        } catch (SQLException e) {e.printStackTrace();
            LOGGER.log(Level.WARNING, type.getName() + "DAO:Insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return t;
    }

    /***
     * Creaza interogarea Delete
     * @param t
     * @return stringul
     */
    public String deleteQuery(T t){
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM magazin.");
        sb.append(type.getSimpleName());
        sb.append(" WHERE id = ");

        Field idField = type.getDeclaredFields()[0];
        idField.setAccessible(true);
        Object value;

        try {
            value = idField.get(t);
            sb.append(value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /***
     * Sterge obiectul t
     * @param t
     * @return t
     */
    public T delete(T t){
        Connection connection = null;
        PreparedStatement statement = null;
        String query = deleteQuery(t);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:Delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return t;
    }

    /***
     * Creaza interogarea UPDATE
     * @param t
     * @return stringul
     */
    public String updateQuery(T t) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE magazin.");
        sb.append(type.getSimpleName());
        sb.append(" SET ");

        String prefix = "";
        Object value;
        try {
            for (Field field : type.getDeclaredFields()) {
                String fieldName = field.getName();
                field.setAccessible(true);
                sb.append(prefix);
                prefix = ",";
                sb.append(fieldName);
                sb.append(" = ");

                value = field.get(t);
                if(value instanceof String)
                    sb.append("'");
                sb.append(value.toString());
                if(value instanceof String)
                    sb.append("'");
            }
            sb.append(" WHERE id = ");
            Field idField = type.getDeclaredFields()[0];
            idField.setAccessible(true);
            value = idField.get(t);
            sb.append(value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    /***
     * Actualizeaza datele despre obiectul t
     * @param t
     * @return t
     */
    public T update(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = updateQuery(t);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return t;
    }

    /***
     * Creaza tabelul
     * @param list - lista cu obiectele care urmeaza sa fie puse in tabel
     * @return JTabel
     */
    public JTable createTable(List<T> list) {
        ArrayList<String> columns = new ArrayList<>();

        for(Field f: type.getDeclaredFields()){
            f.setAccessible(true);
            columns.add(f.getName());
        }
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(columns.toArray());
        for(Object o: list){
            ArrayList<Object> objects = new ArrayList<>();
            try {
                for (Field f : type.getDeclaredFields()) {
                    f.setAccessible(true);
                    objects.add(f.get(o));
                }
            }catch(IllegalAccessException exception){
                exception.printStackTrace();
            }
            tableModel.addRow(objects.toArray());
        }
        JTable table = new JTable(tableModel);
        table.getColumnModel().getColumn(0).setPreferredWidth(20);

        return table;
    }
}

