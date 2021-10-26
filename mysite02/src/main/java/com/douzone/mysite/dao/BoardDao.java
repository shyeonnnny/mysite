package com.douzone.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;

public class BoardDao {
	
	public List<BoardVo> searchFile(String kwd, Long p) {
		List<BoardVo> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = " select a.no, a.title, a.hit, a.reg_date,"
					+ "			a.depth, b.name, a.user_no"
					+ "		from board a, user b"
					+ "		where a.user_no = b.no"
					+ "		and (title like %?% or contents like %?%)"
					+ "		order by group_no desc, order_no asc"
					+ "		limit ?, 10";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, kwd);
			pstmt.setString(2, kwd);
			pstmt.setLong(3, (p-1));
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				Long hit = rs.getLong(3);
				String reg_date = rs.getString(4);
				int depth = rs.getInt(5);
				String name = rs.getString(6);
				Long user_no = rs.getLong(7);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setHit(hit);
				vo.setReg_date(reg_date);
				vo.setDepth(depth);
				vo.setUser_no(user_no);
				vo.setUser_name(name);

				list.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	public List<BoardVo> findPage(long p) {
		List<BoardVo> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = "select a.no, a.title, a.contents, a.hit, a.reg_date, a.group_no, a.order_no, a.depth, b.no as user_no, b.name "
					+ "		from board a, user b " + "    where a.user_no = b.no "
					+ "    order by a.group_no desc, a.order_no asc limit ?,10 ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, (p-1));
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);
				Long hit = rs.getLong(4);
				String reg_date = rs.getString(5);
				int group_no = rs.getInt(6);
				int order_no = rs.getInt(7);
				int depth = rs.getInt(8);
				Long user_no = rs.getLong(9);
				String name = rs.getString(10);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContents(contents);
				vo.setHit(hit);
				vo.setReg_date(reg_date);
				vo.setGroup_no(group_no);
				vo.setOrder_no(order_no);
				vo.setDepth(depth);
				vo.setUser_no(user_no);
				vo.setUser_name(name);

				list.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	public Long countBoardNum() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		long countBoardNum = 0;

		try {
			conn = getConnection();

			String sql = "select count(no) from board";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			rs.next();
			countBoardNum = rs.getLong(1);

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return countBoardNum;
	}
	
	public boolean replyInsert(BoardVo vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();

			String sql = "insert into board(no, title, contents, hit, reg_date, group_no, order_no, depth, user_no) values(null, ?, ?, 0 , now(), ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setInt(3, vo.getGroup_no());
			pstmt.setInt(4, vo.getOrder_no());
			pstmt.setInt(5, vo.getDepth());
			pstmt.setLong(6, vo.getUser_no());

			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	
	public boolean UpdateOrderNo2(BoardVo vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();

			String sql = "update board set order_no = order_no + 1 where group_no = ? and order_no >= ? and depth = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, vo.getGroup_no());
			pstmt.setInt(2, vo.getOrder_no());
			pstmt.setInt(3, vo.getDepth());

			int count = pstmt.executeUpdate();
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	
	public boolean UpdateOrderNo1(BoardVo vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();

			String sql = "update board set order_no = order_no + 1 where group_no = ? and order_no > ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, vo.getGroup_no());
			pstmt.setInt(2, vo.getOrder_no());

			int count = pstmt.executeUpdate();
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	
	
	
	
	public boolean hitUpdate(Long no) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();

			String sql = "update board set hit = hit+1 where no=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, no);

			int count = pstmt.executeUpdate();
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	
	
	public BoardVo findByNo(Long no) {
		BoardVo vo = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = " select title, contents, user_no, group_no, order_no, depth" + "		from board" + "    where no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				String title = rs.getString(1);
				String contents = rs.getString(2);
				Long user_no = rs.getLong(3);
				int group_no = rs.getInt(4);
				int order_no = rs.getInt(5);
				int depth = rs.getInt(6);

				vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContents(contents);
				vo.setUser_no(user_no);
				vo.setGroup_no(group_no);
				vo.setOrder_no(order_no);
				vo.setDepth(depth);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return vo;
	}

	public boolean update(BoardVo vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();

			String sql = "update board set contents = ?, title = ? where no=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getContents());
			pstmt.setString(2, vo.getTitle());
			pstmt.setLong(3, vo.getNo());

			int count = pstmt.executeUpdate();
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public BoardVo BoardView(Long no) {
		BoardVo vo = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = " select title, contents, user_no" + "		from board" + "    where no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				String title = rs.getString(1);
				String contents = rs.getString(2);
				Long user_no = rs.getLong(3);

				vo = new BoardVo();
				vo.setTitle(title);
				vo.setContents(contents);
				vo.setUser_no(user_no);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return vo;
	}

	public boolean delete(BoardVo vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();

			String sql = " delete" + "   from board" + "  where no=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, vo.getNo());

			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public List<BoardVo> findAll() {
		List<BoardVo> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = "select a.no, a.title, a.contents, a.hit, a.reg_date, a.group_no, a.order_no, a.depth, b.no as user_no, b.name "
					+ "		from board a, user b " + "    where a.user_no = b.no "
					+ "    order by a.group_no desc, a.order_no asc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);
				Long hit = rs.getLong(4);
				String reg_date = rs.getString(5);
				int group_no = rs.getInt(6);
				int order_no = rs.getInt(7);
				int depth = rs.getInt(8);
				Long user_no = rs.getLong(9);
				String name = rs.getString(10);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContents(contents);
				vo.setHit(hit);
				vo.setReg_date(reg_date);
				vo.setGroup_no(group_no);
				vo.setOrder_no(order_no);
				vo.setDepth(depth);
				vo.setUser_no(user_no);
				vo.setUser_name(name);

				list.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public boolean insertNewContents(BoardVo vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();

			String sql = "insert into board values (null, ?, ?,0, now(), (select max(group_no) from board as group_max) + 1 , 1, 0, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getUser_no());

			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3306/webdb?characterEncoding=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}

		return conn;
	}

}
