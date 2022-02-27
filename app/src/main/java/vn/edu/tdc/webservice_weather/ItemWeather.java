package vn.edu.tdc.webservice_weather;

public class ItemWeather {
    // Khai bao bien
    private int image;
    private String nhietDo;
    private int tamNhin;
    private double tocDoGo;
    private String thoiGian;

    // Get - set

    public String getNhietDo() {
        return nhietDo;
    }

    public void setNhietDo(String nhietDo) {
        this.nhietDo = nhietDo;
    }

    public int getTamNhin() {
        return tamNhin;
    }

    public void setTamNhin(int tamNhin) {
        this.tamNhin = tamNhin;
    }

    public double getTocDoGo() {
        return tocDoGo;
    }

    public void setTocDoGo(double tocDoGo) {
        this.tocDoGo = tocDoGo;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    // Contructor
    public ItemWeather(int image, String nhietDo, int tamNhin, double tocDoGo, String thoiGian) {
        this.image = image;
        this.nhietDo = nhietDo;
        this.tamNhin = tamNhin;
        this.tocDoGo = tocDoGo;
        this.thoiGian = thoiGian;
    }

    // toString
    @Override
    public String toString() {
        return "ItemWeather{" +
                "image=" + image +
                ", nhietDo='" + nhietDo + '\'' +
                ", tamNhin=" + tamNhin +
                ", tocDoGo=" + tocDoGo +
                ", thoiGian='" + thoiGian + '\'' +
                '}';
    }
}
