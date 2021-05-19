package gpsril;

public class GpsRil {

    private long unixTime;
    private String time;
    private double lat;
    private double longi;
    private double alt;
    private double dist;

    //COSTRUTTORI
    public GpsRil(long unixTime, String time, double lat, double longi, double alt, double dist) {
        this.unixTime = unixTime;
        this.time = time;
        this.lat = lat;
        this.longi = longi;
        this.alt = alt;
        this.dist = dist;
    }

    public GpsRil() {
        this.unixTime = 0;
        this.time = "";
        this.lat = 0.0;
        this.longi = 0.0;
        this.alt = 0.0;
        this.dist = 0.0;
    }

    public GpsRil(GpsRil a) {
        this.unixTime = a.unixTime;
        this.time = a.time;
        this.lat = a.lat;
        this.longi = a.longi;
        this.alt = a.alt;
        this.dist = a.dist;
    }

    //SET
    public void setUnixTime(long unixTime) {
        this.unixTime = unixTime;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLongi(double longi) {
        this.longi = longi;
    }

    public void setAlt(double alt) {
        this.alt = alt;
    }

    public void setDist(double dist) {
        this.dist = dist;
    }

    //GET
    public long getUnixTime() {
        return unixTime;
    }

    public String getTime() {
        return time;
    }

    public double getLat() {
        return lat;
    }

    public double getLongi() {
        return longi;
    }

    public double getAlt() {
        return alt;
    }

    public double getDist() {
        return dist;
    }

    public boolean equals(GpsRil a) {
        return this.toString().equals(a.toString());
    }

    public double distanza(GpsRil a) {
        double lat1 = Math.toRadians(this.getLat());
        double lon1 = Math.toRadians(this.getLongi());
        double lat2 = Math.toRadians(a.getLat());
        double lon2 = Math.toRadians(a.getLongi());

        double earthRadius = 6371.01; //Kilometers
        return earthRadius * Math.acos(Math.sin(lat1)*Math.sin(lat2) + Math.cos(lat1)*Math.cos(lat2)*Math.cos(lon1 - lon2));
    }


    @Override
    public String toString() {
        return "unixTime= " + unixTime + ", time= " + time + ", lat= " + lat + ", longi= " + longi + ", alt= " + alt + ", dist= " + dist;
    }

    public static void main(String[] args) {
        GpsRil r1 = new GpsRil(1455985971,"2016-02-20T16:32:51Z",41.91,12.45,10.0,95269.0);
        GpsRil r2 = new GpsRil(1455985972,"2016-02-20T16:32:52Z",45.48,9.18,10.0,952675.0);
        double dist = r1.distanza(r2);
        System.out.println(dist+" km");
    }
}
