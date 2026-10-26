
# ============================================================
# Simulacion y analisis de señales con la transformada de Fourier
# ============================================================

import numpy as np
import matplotlib.pyplot as plt

# 1. CONFIGURACIÓN DEL MUESTREO
# Frecuencia de muestreo: 1000 muestras por segundo
fs = 1000
T = 1.0
N = int(fs * T)

# Vector de tiempo: desde 0 hasta antes de T
t = np.arange(N) / fs

# Vector de frecuencias para representar el espectro
frecuencias = np.fft.fftfreq(N, d=1/fs)

# 2. GENERACIÓN DE LAS SEÑALES ELEMENTALES

# Pulso rectangular: vale 1 durante un intervalo de 0.2 s
pulso = np.where((t >= 0.2) & (t < 0.4), 1.0, 0.0)

# Función escalón: pasa de 0 a 1 en t = 0.5 s
escalon = np.where(t >= 0.5, 1.0, 0.0)

# Señal senoidal de 50 Hz y amplitud 1
f0 = 50
senoidal = np.sin(2 * np.pi * f0 * t)

# 3. FUNCIÓN PARA CALCULAR LA FFT Y GRAFICAR
def analizar_senal(senal, nombre):
    # Calculamos la Transformada Discreta de Fourier
    espectro = np.fft.fft(senal)

    # Normalizamos la magnitud para facilitar su interpretación
    magnitud = np.abs(espectro) / N

    # Calculamos la fase en radianes
    fase = np.angle(espectro)

    # Seleccionamos las frecuencias positivas
    mitad = N // 2
    f_pos = frecuencias[:mitad]
    mag_pos = magnitud[:mitad]

    # Gráficas: tiempo, magnitud y fase
    fig, ejes = plt.subplots(3, 1, figsize=(10, 9))

    ejes[0].plot(t, senal)
    ejes[0].set_title(f"{nombre}: dominio del tiempo")
    ejes[0].set_xlabel("Tiempo (s)")
    ejes[0].set_ylabel("Amplitud")
    ejes[0].grid(True)

    ejes[1].plot(f_pos, mag_pos)
    ejes[1].set_title(f"{nombre}: espectro de magnitud")
    ejes[1].set_xlabel("Frecuencia (Hz)")
    ejes[1].set_ylabel("Magnitud")
    ejes[1].set_xlim(0, 200)
    ejes[1].grid(True)

    ejes[2].plot(f_pos, fase[:mitad])
    ejes[2].set_title(f"{nombre}: espectro de fase")
    ejes[2].set_xlabel("Frecuencia (Hz)")
    ejes[2].set_ylabel("Fase (rad)")
    ejes[2].set_xlim(0, 200)
    ejes[2].grid(True)

    plt.tight_layout()
    plt.show()

    return espectro

# 4. ANÁLISIS DE LAS TRES SEÑALES
X_pulso = analizar_senal(pulso, "Pulso rectangular")
X_escalon = analizar_senal(escalon, "Función escalón")
X_senoidal = analizar_senal(senoidal, "Señal senoidal")

# 5. PROPIEDAD DE LINEALIDAD
# FFT(a*x + b*y) = a*FFT(x) + b*FFT(y)
a = 2
b = 3

senal_combinada = a * pulso + b * senoidal

FFT_combinada = np.fft.fft(senal_combinada)
FFT_lineal = a * X_pulso + b * X_senoidal

error_linealidad = np.max(np.abs(FFT_combinada - FFT_lineal))

print("PROPIEDAD DE LINEALIDAD")
print("Error máximo:", error_linealidad)
print("Se cumple si el error es aproximadamente cero.")

plt.figure(figsize=(10, 4))
plt.plot(t, senal_combinada)
plt.title("Combinación lineal de señales")
plt.xlabel("Tiempo (s)")
plt.ylabel("Amplitud")
plt.grid(True)
plt.show()

# 6. PROPIEDAD DEL DESPLAZAMIENTO TEMPORAL
# Desplazar la señal cambia la fase, pero conserva la magnitud.
desplazamiento_muestras = 100  # 0.1 segundos
pulso_desplazado = np.roll(pulso, desplazamiento_muestras)

X_original = np.fft.fft(pulso)
X_desplazado = np.fft.fft(pulso_desplazado)

print("\nPROPIEDAD DE DESPLAZAMIENTO")
print("Diferencia máxima de magnitudes:",
      np.max(np.abs(np.abs(X_original) - np.abs(X_desplazado))))

plt.figure(figsize=(10, 4))
plt.plot(t, pulso, label="Pulso original")
plt.plot(t, pulso_desplazado, "--", label="Pulso desplazado")
plt.title("Desplazamiento temporal")
plt.xlabel("Tiempo (s)")
plt.ylabel("Amplitud")
plt.legend()
plt.grid(True)
plt.show()

# Comparación de magnitudes espectrales
plt.figure(figsize=(10, 4))
plt.plot(frecuencias[:N//2], np.abs(X_original[:N//2])/N,
         label="Original")
plt.plot(frecuencias[:N//2], np.abs(X_desplazado[:N//2])/N,
         "--", label="Desplazado")
plt.title("Magnitud antes y después del desplazamiento")
plt.xlabel("Frecuencia (Hz)")
plt.ylabel("Magnitud")
plt.xlim(0, 200)
plt.legend()
plt.grid(True)
plt.show()

# 7. ESCALAMIENTO EN FRECUENCIA
# Comparamos senoidales de 50 Hz y 100 Hz.
senoidal_50 = np.sin(2 * np.pi * 50 * t)
senoidal_100 = np.sin(2 * np.pi * 100 * t)

X_50 = np.fft.fft(senoidal_50)
X_100 = np.fft.fft(senoidal_100)

plt.figure(figsize=(10, 4))
plt.plot(frecuencias[:N//2], np.abs(X_50[:N//2])/N,
         label="Senoidal de 50 Hz")
plt.plot(frecuencias[:N//2], np.abs(X_100[:N//2])/N,
         label="Senoidal de 100 Hz")
plt.title("Comparación del escalamiento en frecuencia")
plt.xlabel("Frecuencia (Hz)")
plt.ylabel("Magnitud")
plt.xlim(0, 150)
plt.legend()
plt.grid(True)
plt.show()

# 8. CONCLUSIONES NUMÉRICAS
print("\nRESULTADOS DEL ANÁLISIS")
print("Frecuencia de muestreo:", fs, "Hz")
print("Número de muestras:", N)
print("Frecuencia de la senoide original:", f0, "Hz")
print("La senoide de 100 Hz presenta el pico en una frecuencia mayor.")
