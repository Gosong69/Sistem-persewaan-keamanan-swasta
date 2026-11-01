import java.util.ArrayList;
import java.util.Scanner;

// Kelas Induk (Superclass)
abstract class UnitMiliter {
    private String nama;
    private String tipe;
    private double hargaPerHari;
    private int sisaHariKontrak;
    private boolean statusDisewa;

    public UnitMiliter(String nama, String tipe, double hargaPerHari) {
        this.nama = nama;
        this.tipe = tipe;
        this.hargaPerHari = hargaPerHari;
        this.sisaHariKontrak = 0;
        this.statusDisewa = false;
    }

    // Getter dan Setter
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public double getHargaPerHari() {
        return hargaPerHari;
    }

    public void setHargaPerHari(double hargaPerHari) {
        this.hargaPerHari = hargaPerHari;
    }

    public int getSisaHariKontrak() {
        return sisaHariKontrak;
    }

    public void setSisaHariKontrak(int sisaHariKontrak) {
        this.sisaHariKontrak = sisaHariKontrak;
    }

    public boolean isStatusDisewa() {
        return statusDisewa;
    }

    public void setStatusDisewa(boolean statusDisewa) {
        this.statusDisewa = statusDisewa;
    }

    // Method untuk menyewa unit
    public boolean sewaUnit(int lamaHari) {
        if (this.statusDisewa || this.sisaHariKontrak > 0) {
            System.out.println("Maaf, " + this.nama + " sedang dalam masa kontrak dan tidak bisa disewa.");
            return false;
        } else {
            this.statusDisewa = true;
            this.sisaHariKontrak = lamaHari;
            System.out.println("Berhasil menyewa " + this.nama + " selama " + lamaHari + " hari.");
            System.out.println("Total Biaya: $" + (this.hargaPerHari * lamaHari));
            return true;
        }
    }

    // Method untuk mensimulasikan berjalannya waktu
    public void kurangiHariKontrak(int hariBerlalu) {
        if (this.statusDisewa) {
            this.sisaHariKontrak -= hariBerlalu;
            if (this.sisaHariKontrak <= 0) {
                this.sisaHariKontrak = 0;
                this.statusDisewa = false;
                System.out.println("Kontrak " + this.nama + " telah berakhir. Unit kembali tersedia.");
            }
        }
    }

    public void tampilkanInfo() {
        String status = statusDisewa ? "Disewa (" + sisaHariKontrak + " hari tersisa)" : "Tersedia";
        System.out.printf("- %-15s | Tipe: %-10s | Harga: $%-7.2f/hari | Status: %s\n", 
                          nama, tipe, hargaPerHari, status);
    }
}

// Subclass Individu
class Individu extends UnitMiliter {
    private String keahlian;

    public Individu(String nama, double hargaPerHari, String keahlian) {
        super(nama, "Individu", hargaPerHari);
        this.keahlian = keahlian;
    }

    public String getKeahlian() { return keahlian; }
    public void setKeahlian(String keahlian) { this.keahlian = keahlian; }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("  Keahlian Spesifik: " + keahlian);
    }
}

// Subclass Regu
class Regu extends UnitMiliter {
    private int jumlahPersonel;

    public Regu(String nama, double hargaPerHari, int jumlahPersonel) {
        super(nama, "Regu", hargaPerHari);
        this.jumlahPersonel = jumlahPersonel;
    }

    public int getJumlahPersonel() { return jumlahPersonel; }
    public void setJumlahPersonel(int jumlahPersonel) { this.jumlahPersonel = jumlahPersonel; }
}

// Subclass Kompi
class Kompi extends UnitMiliter {
    private String spesialisasiTempur;

    public Kompi(String nama, double hargaPerHari, String spesialisasiTempur) {
        super(nama, "Kompi", hargaPerHari);
        this.spesialisasiTempur = spesialisasiTempur;
    }

    public String getSpesialisasiTempur() { return spesialisasiTempur; }
    public void setSpesialisasiTempur(String spesialisasiTempur) { this.spesialisasiTempur = spesialisasiTempur; }
}

// Main Class
public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<UnitMiliter> daftarUnit = new ArrayList<>();

        // Inisialisasi Data
        daftarUnit.add(new Individu("John 'Reaper' Doe", 500, "Sniper / Recon"));
        daftarUnit.add(new Individu("Jane 'Ghost' Smith", 600, "Infiltrasi / CQB"));
        daftarUnit.add(new Regu("Alpha Squad", 3000, 6));
        daftarUnit.add(new Regu("Bravo Squad", 2800, 5));
        daftarUnit.add(new Kompi("Shadow Company", 15000, "Peperangan Skala Penuh & Logistik"));

        boolean isRunning = true;

        System.out.println("===========================================");
        System.out.println("SISTEM PENYEWAAN MILITER SWASTA (PMC)");
        System.out.println("===========================================");

        while (isRunning) {
            System.out.println("\nMenu Utama:");
            System.out.println("1. Tampilkan Daftar Unit");
            System.out.println("2. Sewa Unit");
            System.out.println("3. Simulasikan Waktu Berjalan (Lewati Hari)");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu (1-4): ");
            
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Konsumsi newline

            switch (pilihan) {
                case 1:
                    System.out.println("\n--- DAFTAR UNIT PMC ---");
                    for (int i = 0; i < daftarUnit.size(); i++) {
                        System.out.print((i + 1) + ". ");
                        daftarUnit.get(i).tampilkanInfo();
                    }
                    break;

                case 2:
                    System.out.println("\n--- SEWA UNIT ---");
                    for (int i = 0; i < daftarUnit.size(); i++) {
                        System.out.println((i + 1) + ". " + daftarUnit.get(i).getNama());
                    }
                    System.out.print("Pilih nomor unit yang ingin disewa: ");
                    int nomorUnit = scanner.nextInt();
                    
                    if (nomorUnit > 0 && nomorUnit <= daftarUnit.size()) {
                        System.out.print("Berapa hari Anda ingin menyewa unit ini? ");
                        int lamaSewa = scanner.nextInt();
                        
                        // Memanggil method sewa
                        daftarUnit.get(nomorUnit - 1).sewaUnit(lamaSewa);
                    } else {
                        System.out.println("Pilihan unit tidak valid.");
                    }
                    break;

                case 3:
                    System.out.print("\nBerapa hari yang ingin dilewati? ");
                    int hari = scanner.nextInt();
                    System.out.println("Mensimulasikan waktu berjalan " + hari + " hari...");
                    for (UnitMiliter unit : daftarUnit) {
                        unit.kurangiHariKontrak(hari);
                    }
                    break;

                case 4:
                    System.out.println("Terima kasih telah menggunakan Sistem PMC. Program dihentikan.");
                    isRunning = false;
                    break;

                default:
                    System.out.println("Pilihan tidak valid. Silakan masukkan angka 1-4.");
                    break;
            }
        }
        scanner.close();
    }
}