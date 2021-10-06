package com.example.stepcounterapp.ui
/*
class HomeActivity :AppCompatActivity(), stepsCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, StepDetectorService::class.java)
        startService(intent)

        StepDetectorService.subscribe.register(this)
    }

    override fun subscribeSteps(steps: Int) {
        TV_STEPS.setText(steps.toString())
        TV_CALORIES.setText(GeneralHelper.getCalories(steps))
        TV_DISTANCE.setText(GeneralHelper.getDistanceCovered(steps))
    }
}*/