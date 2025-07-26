import com.undef.ManosLocales.data.local.entities.Seller
import com.undef.ManosLocales.data.mapper.toDomain
import com.undef.ManosLocales.data.remote.ApiService
import com.undef.ManosLocales.data.remote.models.SellerRegisterRequest

import com.undef.ManosLocales.data.repository.SellerRepository

class SellerRepositoryImpl(
    private val apiService: ApiService
) : SellerRepository {

    override suspend fun getSellerByUserId(userId: Int): Seller? {
        val sellerResponse = apiService.getSellerByUserId(userId)
        val userResponse = apiService.getUserById(userId)
        if (sellerResponse.isSuccessful && userResponse.isSuccessful) {
            val sellerDto = sellerResponse.body()
            val userDto = userResponse.body()
            if (sellerDto != null && userDto != null) {
                return sellerDto.toDomain(userDto)
            }
        }
        return null
    }

    override suspend fun deleteSellerByUserId(userId: Int) {
        // Si agregás en el backend un DELETE sellers/deleteByUserId/{userId}, implementalo así:
        // val response = apiService.deleteSellerByUserId(userId)
        // if (!response.isSuccessful) throw Exception("Error al borrar vendedor")

        throw NotImplementedError("Delete seller via API not implemented")
    }

    override suspend fun deleteAllSellers() {
        // Similar, si el backend lo permite:
        // val response = apiService.deleteAllSellers()
        // if (!response.isSuccessful) throw Exception("Error al borrar todos los vendedores")

        throw NotImplementedError("Delete all sellers via API not implemented")
    }

    override suspend fun registerSeller(userId: Int, businessName: String) {
        val sellerRequest = SellerRegisterRequest(
            userId = userId,
            businessName = businessName,
            rating = 0.0
        )
        val response = apiService.createSeller(sellerRequest)
        if (!response.isSuccessful) {
            throw Exception("Error al registrar vendedor: ${response.code()} - ${response.message()}")
        }
    }
    override suspend fun getAllSellers(): List<Seller> {
        val response = apiService.getAllSellers()
        if (response.isSuccessful) {
            val sellerDtos = response.body() ?: emptyList()
            return sellerDtos.map { dto ->
                val user = apiService.getUserById(dto.userId).body() ?: throw Exception("Usuario no encontrado")
                dto.toDomain(user)
            }
        } else {
            throw Exception("Error al obtener vendedores: ${response.code()} - ${response.message()}")
        }
    }


}
