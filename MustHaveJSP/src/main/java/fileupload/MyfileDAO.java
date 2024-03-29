package fileupload;

import java.util.List;
import java.util.Vector;

import common.DBConnPool;

public class MyfileDAO extends DBConnPool	{	//jdbc 연결해서 sql문을 처리한다.
	//새로운 게시물을 입력
	public int insertFile(MyfileDTO dto) {
		int applyResult =0;
		try {
			String query = "INSERT INTO myfile ( idx, name, title, cate, ofile, sfile) "
					+ " VALUES ( seq_board_num.nextval, ?, ?, ?, ?, ?)";
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getCate());
			pstmt.setString(4, dto.getOfile());
			pstmt.setString(5, dto.getSfile());
		
			applyResult = pstmt.executeUpdate();
		}
		catch(Exception e) {
			System.out.println("INSERT 중 예외 발생 (MyfileDAO.insertFile()을 확인하세요");
			e.printStackTrace();
		}
		return applyResult;
	}
	// 파일 목록을 반환합니다.
	public List<MyfileDTO> myFileList(){
		List<MyfileDTO> fileList = new Vector<MyfileDTO>();
		
		// 쿼리문 작성
		String query = "SELECT * FROM myfile ORDER BY idx DESC";
		try {
			pstmt = con.prepareStatement(query);	// 쿼리 준비
			rs = pstmt.executeQuery(); 		// 쿼리 실행
	
			while(rs.next()) {	// 목록 안의 파일 수만큼 반복
				//DTO에 저장
				MyfileDTO dto = new MyfileDTO();
				dto.setIdx(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setCate(rs.getString(4));
				dto.setOfile(rs.getString(5));
				dto.setSfile(rs.getString(6));
				dto.setPostdate(rs.getString(7));
				
				fileList.add(dto);	// 목록에 추가
			}
		}
		catch (Exception e) {
			System.out.println("SELECT 시 예외 발생");
			e.printStackTrace();
		}
		return fileList;	// 목록 반환
	}

}
