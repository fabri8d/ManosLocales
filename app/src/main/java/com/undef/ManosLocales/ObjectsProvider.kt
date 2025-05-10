import com.undef.ManosLocales.Product
import com.undef.ManosLocales.User

object ObjectsProvider {
    val productsList: List<Product> = List(100) { i ->
        val categories = listOf(
            "Higiene", "Decoración", "Accesorios", "Pan",
            "Textil", "Bebida", "Papelería", "Cerámica"
        )

        val users = List(10) { j ->
            User(
                name = "Nombre${j + 1}",
                surname = "Apellido${j + 1}",
                dateOfBirth = "199${j}-01-01",
                username = "usuario${j + 1}",
                email = "usuario${j + 1}@mail.com",
                password = "password${j + 1}"
            )
        }

        val (name, description) = generateRandomProductData(i)
        val owner = users[i % users.size]

        Product(
            id = i + 1,
            name = name,
            price = (1000..10000).random() + (0..99).random() / 100.0,
            category = categories.random(),
            description = description,
            owner = owner
        )
    }

    private fun generateRandomProductData(index: Int): Pair<String, String> {
        val items = listOf("Vela", "Cuadro", "Taza", "Maceta", "Bolso", "Almohadón", "Espejo", "Lampara", "Sahumador", "Caja decorativa")
        val styles = listOf("de cerámica", "de madera", "artesanal", "minimalista", "reciclado", "tejido", "bordado")
        val name = "${items.random()} ${styles.random()}"
        val description = "Producto artesanal #$index elaborado con dedicación."
        return name to description
    }
}
