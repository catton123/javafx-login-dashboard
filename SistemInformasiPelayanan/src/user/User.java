package user;

public class User {
    private String namaLengkap;
    private String nim;
    private String noHp;
    private String email;
    private String jenisKelamin;
    private String username;
    private String password;

public User(String namaLengkap, String nim, String noHp, String email, String jenisKelamin, String username, String password) {
        this.namaLengkap = namaLengkap;
        this.nim = nim;
        this.noHp = noHp;
        this.email = email;
        this.jenisKelamin = jenisKelamin;
        this.username = username;
        this.password = password;       
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public String getNim() {
        return nim;
    }

    public String getNoHp() {
        return noHp;
    }

    public String getEmail() {
        return email;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public String getUsername() { 
        return username; 
    }

    public String getPassword() { 
        return password; 
    }
}
