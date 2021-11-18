package net.chintan.AngularwithSpring.Model;

public class ResponseBody {

    private String JwtResponse;

    public ResponseBody(String jwtResponse) {
        JwtResponse = jwtResponse;
    }

    public String getJwtResponse() {
        return JwtResponse;
    }

    public void setJwtResponse(String jwtResponse) {
        JwtResponse = jwtResponse;
    }
}
