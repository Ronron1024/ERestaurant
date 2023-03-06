package fr.isen.mihalic.androiderestaurant

object MenuProvider {

    private var menu: MutableMap<Stage, MutableList<MenuItem>> = mutableMapOf()
    private var lastIndex: Int = 0

    fun fetchStage(stage: Stage) {
        if (menu[stage] == null)
            fetch(stage)
    }

    private fun fetch(stage: Stage) {
        menu[stage] = mutableListOf()
        for (i in 0..100) {
            menu[stage]?.add(MenuItem(lastIndex, "$stage$lastIndex"))
            lastIndex++
        }
    }

    fun getMenuFor(stage: Stage): List<MenuItem> {
        return menu[stage] ?: listOf()
    }


}