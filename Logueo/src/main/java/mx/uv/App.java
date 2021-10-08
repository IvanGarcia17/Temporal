package mx.uv;
import com.google.gson.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }
            return "OK";
        });

        before((req, res) -> res.header("Access-Control-Allow-Origin", "*"));

        post("/saludar", (req, res) -> {
            String l = req.queryParams("nombre");
            String p = req.queryParams("password");
            String respuesta;
            System.out.println(l + " " + p);
            
            if (l.equals("root") && p.equals("123123"))
            respuesta = "Bienvenido root <a href='http://127.0.0.1:5500/Lista.html'>Mostrar Datos</a>";
            else
            respuesta = "Usuario no encontrado <a href='http://127.0.0.1:5500/Formulario.html'>Registrarme</a>";
            return respuesta;
        });
    }
}
