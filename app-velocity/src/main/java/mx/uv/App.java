package mx.uv;
import static spark.Spark.*;
import com.google.gson.*;

import mx.uv.datos.Usuario;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * Hello world!
 *
 */
public class App 
{

    private static Gson gson = new Gson();
    private static Map<String, Usuario> usuarios = new HashMap<>();
    
    public static void main( String[] args ) {
        System.out.println("Helo Wordl!");
        port(getHerokuAssignedPort());
        staticFiles.location("/");
        
        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

        //Tiene prevalencia el mapero estático de forma que
        //si tenemos un index.html, este se va a cargar primero que 
        //el mapero de la raíz "/"

        get("/", (req, res) -> {
            res.redirect("/index.html");
            return null;
        });

        get("/hola", (req, res) -> {
            res.redirect("/hola.html");
            return null;
        });

        post("/enviar", (req, res) -> {
            //insertamos un nuvevo usuario
            String json = req.body();
            Usuario u = gson.fromJson(json, Usuario.class);
            String id = UUID.randomUUID().toString();
            u.setId(id);
            usuarios.put(id, u);

            JsonObject respuesta = new JsonObject();
            respuesta.addProperty("status", "creado");
            respuesta.addProperty("id", id);
            return respuesta;
        });

        //do this
        get("/pagina", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new VelocityTemplateEngine().render(new ModelAndView(model, "pagina.html"));
        });

        //do this
        get("/velocity", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("nombre", "Que se yo!");
            return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/hola.vm"));
        });

        //do this
        get("/usario", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("nombre", usuarios.values());
            return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/hola.vm"));
        });
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }


}
