public class MyFile {

    public MyFile(int fileid, String filename, byte[] fileContentBytes, String fileExtension) {
        FileExtension = fileExtension;
    }

    public String FileExtension;

    public int getId() {
        return 0;
    }

    public String getName() {
        return null;
    }

    public byte[] getData() {
        return new byte[0];
    }
}
