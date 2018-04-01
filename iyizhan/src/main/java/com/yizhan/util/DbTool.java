package com.yizhan.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yizhan.bean.User;


public class DbTool {

	private static final Log log = LogFactory.getLog(DbTool.class);
	
	/**
	 * 排除为null的情况
	 */
	private static String nvl(String str){
		if(null!=str){
			return str;
		}
		return "";
	}
	
	/**
	 * 查询：返回map集合	不指定列数	格式：key：value 列名：列值
	 */
	public static List<Map<String, String>> getMapList(String sql){
		log.info(sql);
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = DbUtil.getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			ResultSetMetaData rsm = rs.getMetaData();
			while(rs.next()){
				Map<String, String> tmap = new LinkedHashMap<String, String>();
				for (int i = 0; i < rsm.getColumnCount(); i++) {
					tmap.put(rsm.getColumnName(i).toLowerCase(), nvl(rs.getString(rsm.getColumnName(i))));
				}
				mapList.add(tmap);
			}
		}catch(SQLException e){
			log.error("SQL执行错误！");
		}finally {
			DbUtil.closeConnection(ps, rs);
			DbUtil.closeConnection();
		}
		return mapList;
	}
	
	/**
	 * 查询：返回map集合	指定列数	格式：key：value 列名：列值
	 */
	public static List<Map<String, String>> getMapList(String sql,int colnum){
		log.info(sql);
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = DbUtil.getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				Map<String, String> tmap = new LinkedHashMap<String, String>();
				for (int i = 0; i < colnum; i++) {
					tmap.put(String.valueOf(i), rs.getString(i+1));
				}
				mapList.add(tmap);
			}
		}catch(SQLException e){
			log.error("SQL执行错误！");
		}finally {
			DbUtil.closeConnection(ps, rs);
			DbUtil.closeConnection();
		}
		return mapList;
	}
	
	/**
	 * SQL查询 获取Map 格式：key：value 列名：列值
	 */
	public static Map<String, String> getColMap(String sql){
		log.info(sql);
		Map<String, String> tmap = new LinkedHashMap<String, String>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = DbUtil.getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			ResultSetMetaData rsm = rs.getMetaData();
			while(rs.next()){
				for (int i = 0; i < rsm.getColumnCount(); i++) {
					tmap.put(rsm.getColumnName(i).toLowerCase(), String.valueOf(rs.getObject(rsm.getColumnName(i))));
				}
			}
		}catch(SQLException e){
			log.error("SQL执行错误！");
		}finally {
			DbUtil.closeConnection(ps, rs);
			DbUtil.closeConnection();
		}
		return tmap;
	}
	
	/**
	 * 执行DML(数据控制语句-insert/update/delete)语句
	 */
	public static boolean updateBySQL(String sql){
		log.info(sql);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = DbUtil.getConnection().prepareStatement(sql);
			int r = ps.executeUpdate();
			if(r<0){
				return false;
			}
		}catch(SQLException e){
			log.error("SQL执行错误！");
		}finally {
			DbUtil.closeConnection(ps, rs);
			DbUtil.closeConnection();
		}
		return true;
	}
	
	/**
	 * 指定个数，查询返回数组结果集
	 */
	public static String[] queryStringArrayBySQL(String sql, int num){
		log.info(sql);
		PreparedStatement ps = null;
		ResultSet rs = null;
		String[] arrString = new String[num];
		try{
			ps = DbUtil.getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				for (int i = 0; i < num; i++) {
					arrString[i] = rs.getString(i+1);
				}
			}
		}catch(SQLException e){
			log.error("SQL执行错误！");
		}finally {
			DbUtil.closeConnection(ps, rs);
			DbUtil.closeConnection();
		}
		return arrString;
	}
	
	/**
	 * 查询方法 返回单个字段字符串
	 */
	public static String queryStringBySQL(String sql){
		log.info(sql);
		PreparedStatement ps = null;
		ResultSet rs = null;
		String column = "";
		try{
			ps = DbUtil.getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				column = rs.getString(1);
			}
		}catch(SQLException e){
			log.error("SQL执行错误！");
		}finally {
			DbUtil.closeConnection(ps, rs);
			DbUtil.closeConnection();
		}
		return column;
	}
	
	/**
	 * 查询方法 返回单个字段字符串
	 */
	public static int queryIntBySQL(String sql){
		log.info(sql);
		PreparedStatement ps = null;
		ResultSet rs = null;
		int column = 0;
		try{
			ps = DbUtil.getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				column = rs.getInt(1);
			}
		}catch(SQLException e){
			log.error("SQL执行错误！");
		}finally {
			DbUtil.closeConnection(ps, rs);
			DbUtil.closeConnection();
		}
		return column;
	}
	
	/**
	 * 查询  指定个数  返回数组集合结果
	 */
	public static List<String[]> queryStringArrListBySQL(String sql, int num){
		log.info(sql);
		List<String[]> arrList = new ArrayList<String[]>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = DbUtil.getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				String[] arrString = new String[num];
				for (int i = 0; i < num; i++) {
					arrString[i] = rs.getString(i+1)==null? "" : rs.getString(i+1);
				}
				arrList.add(arrString);
			}
		}catch(SQLException e){
			log.error("SQL执行错误！");
		}finally {
			DbUtil.closeConnection(ps, rs);
			DbUtil.closeConnection();
		}
		return arrList;
	}
	
	/**
	 * 查询：返回单列数据集合
	 */
	public static List<String> queryListBySQL(String sql){
		log.info(sql);
		List<String> list = new ArrayList<String>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = DbUtil.getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				list.add(rs.getString(1));
			}
		}catch(SQLException e){
			log.error("SQL执行错误！");
		}finally {
			DbUtil.closeConnection(ps, rs);
			DbUtil.closeConnection();
		}
		return list;
	}
	
	/**
	 * 批量执行SQL语句
	 */
	public static void batchSaveSQL(List<String> sqlList){
		Connection conn = null;
		Statement st = null;
		try{
			conn = DbUtil.getConnection();
			conn.setAutoCommit(false);
			st = conn.createStatement();
			for (int i = 0; i < sqlList.size(); i++) {
				st.addBatch(sqlList.get(i));
				if(i%500==0 || i==sqlList.size()-1){//每500条SQL执行一次
					st.executeBatch();
					conn.commit();
					st.clearBatch();
				}
			}
			st.executeBatch();
			conn.commit();
			st.clearBatch();
		}catch(SQLException e){
			log.error("SQL执行错误！");
		}finally {
			try {
				st.close();
				DbUtil.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 获取表主键
	 */
	public static String getTablePK(String tablename){
		String tablespace = "";
		if(tablename.indexOf(".")>-1){
			tablename = tablename.split("\\.")[1];
			tablespace = tablename.split("\\.")[0];
		}
		String pk = null;
		DatabaseMetaData dbms = null;
		ResultSet rs = null;
		try{
			dbms = DbUtil.getConnection().getMetaData();
			rs = dbms.getPrimaryKeys(tablespace, null, tablename);
			while(rs.next()){
				pk = rs.getString("COLUMN_NAME");
			}
		}catch(SQLException e){
			log.error("SQL执行错误！");
		}finally {
			DbUtil.closeConnection(null, rs);
			DbUtil.closeConnection();
		}
		return pk.toLowerCase();
	}
	
	/**
	 * 查询所有的列名
	 */
	public static List<String> queryColumns(String tablename){
		List<String> columns = new ArrayList<String>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select column_name from information_schema.columns where table_name='" + tablename + "'";
		try{
			ps = DbUtil.getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				columns.add(rs.getString("column_name"));
			}
		}catch(SQLException e){
			e.printStackTrace();
			log.error("SQL执行错误！");
		}finally {
			DbUtil.closeConnection(ps, rs);
			DbUtil.closeConnection();
		}
		return columns;
	}
	
	
	/**
	 * 查询对象
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T>T getObject(String sql, Class<T> cls, String...args){
		log.info(sql);
		Object obj = null;
		Method[] ms = cls.getDeclaredMethods();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = DbUtil.getConnection().prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				ps.setString(i+1, args[i]);
			}
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int colCount = rsmd.getColumnCount();
			if(rs.next()){
				obj = cls.newInstance();
				for (int i = 1; i <= colCount; i++) {
					String colName = rsmd.getColumnName(i);
					for (int j = 0; j < ms.length; j++) {
						Method method = ms[j];
						Class[] cs = method.getParameterTypes();
						if(method.getName().equalsIgnoreCase("set"+colName)){
							if(cs[0] == int.class){
								method.invoke(obj, rs.getInt(i));
								break;
							}
							if(cs[0] == long.class){
								method.invoke(obj, rs.getLong(i));
								break;
							}
							if(cs[0] == Date.class){
								method.invoke(obj, rs.getDate(i));
								break;
							}
							if(cs[0] == String.class){
								method.invoke(obj, rs.getString(i));
								break;
							}
						}
					}
				}
			}
		}catch(Exception e){
			log.error("SQL执行错误！");
		}finally {
			DbUtil.closeConnection(ps, rs);
			DbUtil.closeConnection();
		}
		return (T) obj;
	}
	
	/**
	 * 查询对象集合
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T>List<T> getObjectList(String sql, Class<T> cls, String...args){
		log.info(sql);
		List<T> list = new ArrayList<T>();
		Method[] ms = cls.getDeclaredMethods();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = DbUtil.getConnection().prepareStatement(sql);
			if(args!=null){
				for (int i = 0; i < args.length; i++) {
					ps.setString(i+1, args[i]);
				}
			}
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int colCount = rsmd.getColumnCount();
			while(rs.next()){
				Object obj = cls.newInstance();
				for (int i = 1; i <= colCount; i++) {
					String colName = rsmd.getColumnName(i);
					for (int j = 0; j < ms.length; j++) {
						Method method = ms[j];
						Class[] cs = method.getParameterTypes();
						if(method.getName().equalsIgnoreCase("set"+colName)){
							if(cs[0] == int.class){
								method.invoke(obj, rs.getInt(i));
								break;
							}
							if(cs[0] == long.class){
								method.invoke(obj, rs.getLong(i));
								break;
							}
							if(cs[0] == Date.class){
								method.invoke(obj, rs.getDate(i));
								break;
							}
							if(cs[0] == String.class){
								method.invoke(obj, rs.getString(i));
								break;
							}
							if(cs[0] == double.class){
								method.invoke(obj, rs.getDouble(i));
								break;
							}
							if(cs[0] == boolean.class){
								method.invoke(obj, rs.getBoolean(i));
								break;
							}
						}
					}
				}
				list.add((T) obj);
			}
		}catch(Exception e){
			log.error("SQL执行错误！");
		}finally {
			DbUtil.closeConnection(ps, rs);
			DbUtil.closeConnection();
		}
		return list;
	}
	
	/**
	 * 对象进行增删改查
	 */
	public static void executeObject(String sql, Object...objs){
		PreparedStatement ps = null;
		try{
			ps = DbUtil.getConnection().prepareStatement(sql);
			for (int i = 0; objs!=null && i<objs.length; i++) {
				ps.setObject(i+1, objs[i]);
			}
			ps.executeUpdate();
		}catch(SQLException e){
			log.error("SQL执行错误！");
		}finally {
			DbUtil.closeConnection(ps, null);
			DbUtil.closeConnection();
		}
	}
	
	/**
	 * 保存对象集合
	 */
	public static <T> void saveOrUpdateObjects(Class<T> cls, String tablename, List<?> list){
		if(list.size()==0){
			return;
		}
		List<String> columns = new ArrayList<String>();
		Field[] allFiled = cls.getDeclaredFields();
		for (Field field : allFiled) {
			columns.add(field.getName());
		}
		StringBuffer sb = new StringBuffer("insert into " + tablename + " values(");
		for (int i = 0; i < columns.size()-1; i++) {
			sb.append("?,");
		}
		String sql = sb.append("?)").toString();
		Method[] ms = cls.getDeclaredMethods();
		List<Method> methods = new ArrayList<Method>();
		for (String col : columns) {
			for (int j = 0; j < ms.length; j++) {
				Method method = ms[j];
				if(method.getName().equalsIgnoreCase("get" + col)){
					methods.add(method);
				}
			}
		}
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn = DbUtil.getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			log.info(sql);
			for (Object obj : list) {
				for (int i = 0; i < columns.size(); i++) {
					ps.setObject(i+1, methods.get(i).invoke(obj));
				}
				ps.addBatch();
			}
			int[] arr = ps.executeBatch();
			conn.commit();
			log.info("Success Save Objects Num: " + arr.length);
		}catch(Exception e){
			e.printStackTrace();
			log.error("SQL执行错误！");
		}finally {
			DbUtil.closeConnection(ps, null);
			DbUtil.closeConnection();
		}
	}
	
	public static void main(String[] args) {
		User user = new User();
		user.setUid(2);
		user.setUsername("admin");
		user.setPassword("admin1234");
		saveOrUpdateObject(User.class,"userinfo",user);
	}
	
	/**
	 * 保存对象
	 * 当id为空时创建新记录，不为空时覆盖原记录
	 */
	public static <T> boolean saveOrUpdateObject(Class<T> cls, String tablename, Object obj){
		String pk = getTablePK(tablename);
		List<String> columns = queryColumns(tablename);
		StringBuffer sb = new StringBuffer("insert into " + tablename + " values(");
		for (int i = 0; i < columns.size()-1; i++) {
			sb.append("?,");
		}
		String sql = sb.append("?)").toString();
		Method[] ms = cls.getDeclaredMethods();
		List<Method> methods = new ArrayList<Method>();
		for (String col : columns) {
			for (int j = 0; j < ms.length; j++) {
				Method method = ms[j];
				if(method.getName().equalsIgnoreCase("get" + col)){
					methods.add(method);
				}
			}
		}
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		try{
			String delSql = "";
			conn = DbUtil.getConnection();
			ps = conn.prepareStatement(sql);
			log.info(sql);
			for (int i = 0; i < columns.size(); i++) {
				if(columns.get(i).toLowerCase().equals(pk)){
					Object pkId = methods.get(i).invoke(obj);
					delSql = "delete from " + tablename+ " where " +pk+ "='" + pkId + "'";
					ps2 = conn.prepareStatement(delSql);
					ps2.executeUpdate();
				}
				ps.setObject(i+1, methods.get(i).invoke(obj));
			}
			ps.executeUpdate();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			log.error("SQL执行错误！");
			return false;
		}finally {
			DbUtil.closeConnection(ps, null);
			DbUtil.closeConnection(ps2, null);
			DbUtil.closeConnection();
		}	
	}
	
	public static Map<String, String> queryMapData(String sql){
		log.info(sql);
		Map<String, String> tmap = new LinkedHashMap<String, String>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = DbUtil.getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				tmap.put(rs.getString(1), rs.getString(2));
			}
		}catch(SQLException e){
			log.error("SQL执行错误！");
		}finally {
			DbUtil.closeConnection(ps, rs);
			DbUtil.closeConnection();
		}
		return null;
	}
	
	
	
}
