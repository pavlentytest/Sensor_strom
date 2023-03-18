package com.example.myapplication

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    var lightSensor: Sensor? = null
    var rotateSensor: Sensor? = null
    var pressureSensor: Sensor? = null
    var tempSensor: Sensor? = null
    private lateinit var lighttext: TextView
    private lateinit var pressuretext: TextView
    private lateinit var rotateimg: ImageView
    private lateinit var temptext: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        lighttext = findViewById(R.id.lightvalue)
        rotateimg = findViewById(R.id.rotatevalue)
        pressuretext = findViewById(R.id.pressurevalue)
        temptext = findViewById(R.id.tempvalue)

    }

    override fun onResume() {
        super.onResume()
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        if(lightSensor != null) {
            sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_GAME)
        }
        pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE)
        if(pressureSensor != null) {
            sensorManager.registerListener(this, pressureSensor, SensorManager.SENSOR_DELAY_GAME)
        }
        rotateSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        if(rotateSensor != null) {
            sensorManager.registerListener(this, rotateSensor, SensorManager.SENSOR_DELAY_GAME)
        }
        tempSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        if(tempSensor != null) {
            sensorManager.registerListener(this, tempSensor, SensorManager.SENSOR_DELAY_GAME)
        }

    }

    override fun onSensorChanged(event: SensorEvent?) {
        if(lightSensor == null)
            lighttext.text = "No light sensor!"
        else if(event!!.sensor.type == lightSensor!!.type) {
            lighttext.text = "Light: ${event.values[0]}"
        }
        if(pressureSensor == null)
            pressuretext.text = "No pressure sensor!"
        else if(event!!.sensor.type == pressureSensor!!.type) {
            pressuretext.text = "Pressure: ${event.values[0]}"
        }
        //Log.d("RRR", rotateSensor.toString())
        if(event!!.sensor.type == rotateSensor!!.type) {
           rotateimg.rotation = event.values[2]*10
           // Log.d("RRR",event.values.joinToString())
        }
        if(tempSensor == null)
            temptext.text = "No temp sensor!"
        else if(event!!.sensor.type == tempSensor!!.type) {
            temptext.text = "Temp: ${event.values[0]}"
        }

    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }
}