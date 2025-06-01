package com.uisrael.Sistema.Casificacion.Radiografias.Util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class StreamlitLauncher {
    @Bean
    public CommandLineRunner launchPythonServices() {
        return args -> {
            try {
                // 📦 Obtener el directorio donde está el JAR ejecutándose
                String jarDir = new File(StreamlitLauncher.class.getProtectionDomain()
                        .getCodeSource().getLocation().toURI()).getParent();

                // 📂 Construir rutas dinámicamente
                Path streamlitAppPath = Paths.get(jarDir, "streamlit-app");
                String pythonPath = streamlitAppPath.resolve("venv")
                        .resolve("Scripts")
                        .resolve("python.exe")
                        .toString();

                // ▶️ 1. Iniciar FastAPI
                ProcessBuilder fastapiPB = new ProcessBuilder(
                        pythonPath, "-m", "uvicorn", "api:app", "--reload", "--port", "8000"
                );
                fastapiPB.directory(streamlitAppPath.toFile());
                fastapiPB.inheritIO();
                fastapiPB.start();

                // ▶️ 2. Iniciar Streamlit
                ProcessBuilder streamlitPB = new ProcessBuilder(
                        pythonPath, "-m", "streamlit", "run", "clasificador_app.py", "--server.port", "8501"
                );
                streamlitPB.directory(streamlitAppPath.toFile());
                streamlitPB.inheritIO();
                streamlitPB.start();

                System.out.println("✅ Servicios Python iniciados: FastAPI (8000) y Streamlit (8501)");

            } catch (Exception e) {
                System.err.println("❌ Error al iniciar servicios Python: " + e.getMessage());
                e.printStackTrace();
            }
        };
    }
}
