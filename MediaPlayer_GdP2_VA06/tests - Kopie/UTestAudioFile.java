// to test the code on Windows
// isWindows() needs to return true;
import org.junit.Test;
import static org.junit.Assert.*;

public class UTestAudioFile {
	private boolean isWindows() {
		return System.getProperty("os.name").toLowerCase().indexOf("win") >= 0;
		// only for testing
		//		return true;
	}
	
	@Test
	public void test_parsePathname_01() throws Exception {
		AudioFile af = new AudioFile();
		af.parsePathname("");
		char sepchar = java.io.File.separatorChar;

		assertEquals("Pathname stored incorrectly", "", af.getPathname());
		assertEquals("Returned filname is incorrect", "", af.getFilename());
	}
	@Test
	public void test_parsePathname_02() throws Exception {
		AudioFile af = new AudioFile();
		af.parsePathname(" 	 	 	 ");
		char sepchar = java.io.File.separatorChar;

		assertEquals("Pathname stored incorrectly", " 	 	 	 ", af.getPathname());
		assertEquals("Returned filname is incorrect", " 	 	 	 ", af.getFilename());
	}
	@Test
	public void test_parsePathname_03() throws Exception {
		AudioFile af = new AudioFile();
		af.parsePathname("file.mp3");
		char sepchar = java.io.File.separatorChar;

		assertEquals("Pathname stored incorrectly", "file.mp3", af.getPathname());
		assertEquals("Returned filname is incorrect", "file.mp3", af.getFilename());
	}
	@Test
	public void test_parsePathname_04() throws Exception {
		AudioFile af = new AudioFile();
		af.parsePathname("/my-tmp/file.mp3");
		
		if(isWindows()){
			assertEquals("Pathname stored incorrectly", '\\' + "my-tmp" + '\\' + "file.mp3", af.getPathname());
			assertEquals("Returned filname is incorrect", "file.mp3", af.getFilename());
		}else{
			assertEquals("Pathname stored incorrectly", '/' + "my-tmp" + '/' + "file.mp3", af.getPathname());
			assertEquals("Returned filname is incorrect", "file.mp3", af.getFilename());
		}
		
		
	}
	@Test
	public void test_parsePathname_05() throws Exception {
		AudioFile af = new AudioFile();
		af.parsePathname("//my-tmp////part1//file.mp3/");
		char sepchar = java.io.File.separatorChar;
		if(isWindows()){
			assertEquals("Pathname stored incorrectly", "\\my-tmp\\part1\\file.mp3\\", af.getPathname());
			assertEquals("Returned filname is incorrect", "", af.getFilename());
	
		}else{
			assertEquals("Pathname stored incorrectly", "/my-tmp/part1/file.mp3/", af.getPathname());
			assertEquals("Returned filname is incorrect", "", af.getFilename());
		}
	}
	@Test
	public void test_parsePathname_06() throws Exception {
		AudioFile af = new AudioFile();
		af.parsePathname("d:\\\\part1///file.mp3");
		char sepchar = java.io.File.separatorChar;
		if (!isWindows()) {
			// only on linux
			assertEquals("Pathname stored incorrectly", "/d/part1/file.mp3", af.getPathname());
			assertEquals("Returned filname is incorrect", "file.mp3", af.getFilename());
		} else {
			// only on Windows
			assertEquals("Pathname stored incorrectly", "d:" + '\\' + "part1" + '\\' + "file.mp3", af.getPathname());
			assertEquals("Returned filname is incorrect", "file.mp3", af.getFilename());
		}
	}
	@Test
	public void test_parseFilename_11() throws Exception {
		AudioFile af = new AudioFile();
		af.parsePathname("/tmp/test/ Falco - Rock me Amadeus .mp3");
		af.parseFilename(af.getFilename());

		assertEquals("Filename stored incorrectly", " Falco - Rock me Amadeus .mp3", af.getFilename());
		assertEquals("Author stored incorrectly", "Falco", af.getAuthor());
		assertEquals("Title stored incorrectly", "Rock me Amadeus", af.getTitle());
	}
	@Test
	public void test_parseFilename_12() throws Exception {
		AudioFile af = new AudioFile();
		af.parsePathname("/tmp/test/Frankie Goes To Hollywood - The Power Of Love.ogg");
		af.parseFilename(af.getFilename());

		assertEquals("Filename stored incorrectly","Frankie Goes To Hollywood - The Power Of Love.ogg", af.getFilename());
		assertEquals("Author stored incorrectly", "Frankie Goes To Hollywood", af.getAuthor());
		assertEquals("Title stored incorrectly", "The Power Of Love", af.getTitle());
	}
	@Test
	public void test_parseFilename_13() throws Exception {
		AudioFile af = new AudioFile();
		af.parsePathname("audiofile.aux");
		af.parseFilename(af.getFilename());

		assertEquals("Filename stored incorrectly","audiofile.aux", af.getFilename());
		assertEquals("Author stored incorrectly", "", af.getAuthor());
		assertEquals("Title stored incorrectly", "audiofile", af.getTitle());
	}
	@Test
	public void test_parseFilename_14() throws Exception {
		AudioFile af = new AudioFile();
		af.parsePathname("   A.U.T.O.R    -   T.I.T.E.L  .EXTENSION");
		af.parseFilename(af.getFilename());

		assertEquals("Filename stored incorrectly", "   A.U.T.O.R    -   T.I.T.E.L  .EXTENSION", af.getFilename());
		assertEquals("Author stored incorrectly", "A.U.T.O.R", af.getAuthor());
		assertEquals("Title stored incorrectly", "T.I.T.E.L", af.getTitle());
	}
	@Test
	public void test_parseFilename_15() throws Exception {
		AudioFile af = new AudioFile();
		af.parsePathname("Hans-Georg Sonstwas - Blue-eyed boy-friend.mp3");
		af.parseFilename(af.getFilename());

		assertEquals("Filename stored incorrectly", "Hans-Georg Sonstwas - Blue-eyed boy-friend.mp3", af.getFilename());
		assertEquals("Author stored incorrectly", "Hans-Georg Sonstwas", af.getAuthor());
		assertEquals("Title stored incorrectly", "Blue-eyed boy-friend", af.getTitle());
	}
	@Test
	public void test_parseFilename_16() throws Exception {
		AudioFile af = new AudioFile();
		af.parsePathname(".mp3");
		af.parseFilename(af.getFilename());

		assertEquals("Filename stored incorrectly", ".mp3", af.getFilename());
		assertEquals("Author stored incorrectly", "", af.getAuthor());
		assertEquals("Title stored incorrectly", "", af.getTitle());
	}
	@Test
	public void test_parseFilename_17() throws Exception {
		AudioFile af = new AudioFile();
		af.parsePathname("Falco - Rock me Amadeus.");
		af.parseFilename(af.getFilename());

		assertEquals("Filename stored incorrectly", "Falco - Rock me Amadeus.", af.getFilename());
		assertEquals("Author stored incorrectly", "Falco", af.getAuthor());
		assertEquals("Title stored incorrectly", "Rock me Amadeus", af.getTitle());
	}
	@Test
	public void test_parseFilename_18() throws Exception {
		AudioFile af = new AudioFile();
		af.parsePathname("-");
		af.parseFilename(af.getFilename());

		assertEquals("Filename stored incorrectly", "-", af.getFilename());
		assertEquals("Author stored incorrectly", "", af.getAuthor());
		assertEquals("Title stored incorrectly", "-", af.getTitle());
	}
	@Test
	public void test_parseFilename_19() throws Exception {
		AudioFile af = new AudioFile();
		af.parsePathname(" - ");
		af.parseFilename(af.getFilename());

		assertEquals("Filename stored incorrectly", " - ", af.getFilename());
		assertEquals("Author stored incorrectly", "", af.getAuthor());
		assertEquals("Title stored incorrectly", "", af.getTitle());
	}

	@Test
	public void test_parseFilename_20() throws Exception {
		AudioFile af = new AudioFile();
		af.parsePathname("");
		af.parseFilename(af.getFilename());
		assertEquals("Pathname stored incorrectly", "", af.getPathname());
		assertEquals("Filename stored incorrectly", "", af.getFilename());
		assertEquals("toString stored incorrectly", "", af.toString());
		assertEquals("Author stored incorrectly", "", af.getAuthor());
		assertEquals("Title stored incorrectly", "", af.getTitle());
	}
	@Test
	public void test_parseFilename_21() throws Exception {
		AudioFile af = new AudioFile();
		af.parsePathname(" ");
		af.parseFilename(af.getFilename());
		assertEquals("Pathname stored incorrectly", " ", af.getPathname());
		assertEquals("Filename stored incorrectly", " ", af.getFilename());
		assertEquals("toString stored incorrectly", "", af.toString());
		assertEquals("Author stored incorrectly", "", af.getAuthor());
		assertEquals("Title stored incorrectly", "", af.getTitle());
	}
}
