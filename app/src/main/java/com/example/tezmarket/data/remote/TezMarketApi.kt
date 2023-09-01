package com.example.tezmarket.data.remote

import com.example.tezmarket.data.remote.model.AttributesByCategory.AttributesById
import com.example.tezmarket.data.remote.model.addadvertisement.AddAdvertisement
import com.example.tezmarket.data.remote.model.addcart.AddCartProduct
import com.example.tezmarket.data.remote.model.adress.AddAdress
import com.example.tezmarket.data.remote.model.adress.AdressById
import com.example.tezmarket.data.remote.model.adress.AdressesData
import com.example.tezmarket.data.remote.model.adress.UpdateAdress
import com.example.tezmarket.data.remote.model.advertisementbyid.AdvertisementById
import com.example.tezmarket.data.remote.model.advertisements.Advertisements
import com.example.tezmarket.data.remote.model.banners.BannersData
import com.example.tezmarket.data.remote.model.cart.Cart
import com.example.tezmarket.data.remote.model.categories.Categories
import com.example.tezmarket.data.remote.model.delcart.DelCartProduct
import com.example.tezmarket.data.remote.model.deleteAdvertisement.DeleteAdvertisementData
import com.example.tezmarket.data.remote.model.discProducts.DiscProducts
import com.example.tezmarket.data.remote.model.favorites.FavoriteProducts
import com.example.tezmarket.data.remote.model.favorites.FavoritesToggle
import com.example.tezmarket.data.remote.model.filterdata.FilterData
import com.example.tezmarket.data.remote.model.filteredata.FilteredProducts
import com.example.tezmarket.data.remote.model.modcart.ModCartProduct
import com.example.tezmarket.data.remote.model.myadvertisements.MyAdvertisementsData
import com.example.tezmarket.data.remote.model.prodouctsbycategory.ProductByCategory
import com.example.tezmarket.data.remote.model.productbyid.ProductById
import com.example.tezmarket.data.remote.model.productrating.AddProductRating
import com.example.tezmarket.data.remote.model.productrating.ProductRatingData
import com.example.tezmarket.data.remote.model.products.ProductsData
import com.example.tezmarket.data.remote.model.recproducts.RecProducts
import com.example.tezmarket.data.remote.model.shops.ShopsData
import com.example.tezmarket.data.remote.model.shopsbyid.ShopByIdData
import com.example.tezmarket.data.remote.model.simularproducts.SimularProduct
import com.example.tezmarket.data.remote.model.uploadFiles.UploadFiles
import com.example.tezmarket.data.remote.model.user.UserData
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap
import java.io.File


interface TezMarketApi {

    @GET("shops")
    suspend fun getAllShops(
        @Query("page") page: Int
    ): Response<ShopsData>

    @GET("main/products")
    suspend fun getAllProducts(
        @Query("page") page: Int
    ): Response<ProductsData>

    @GET("main/recommended-products")
    suspend fun getRecProducts(
        @Query("page") page: Int
    ): Response<RecProducts>

    @GET("products/{product_id}")
    suspend fun getProductById(
        @Path("product_id") productId: Int
    ): Response<ProductById>

    @GET("advertisements/{advertisement_id}")
    suspend fun getAdvertisementById(
        @Path("advertisement_id") advertisement_id: Int
    ): Response<AdvertisementById>

    @GET("shops/{shop_id}")
    suspend fun getShopById(
        @Path("shop_id") shop_id: Int
    ): Response<ShopByIdData>

    @GET("discounted-products")
    suspend fun getDiscProducts(
        @Query("page") page: Int
    ): Response<DiscProducts>

    @GET("main/banners")
    suspend fun getAllBanners(): Response<BannersData>

    @GET("profile/favorites/products")
    suspend fun getFavoriteProducts(): Response<FavoriteProducts>

    @GET("categories")
    suspend fun getAllCategories(): Response<Categories>

    @GET("categories/{category_id}/products")
    suspend fun getProductsByCategory(
        @Path("category_id") categoryId: Int,
        @Query("page") page: Int
    ): Response<ProductByCategory>

    @GET("profile/carts")
    suspend fun getCartProducts(): Response<Cart>

    @POST("profile/carts")
    suspend fun addCartProduct(
        @Query("product_id") productId: Int,
        @Query("quantity") productQuantity: Int,
    ): Response<AddCartProduct>

    @DELETE("profile/carts/{cart_id}")
    suspend fun delCartProduct(
        @Path("cart_id") cartId: Int
    ): Response<DelCartProduct>

    @PUT("profile/carts/{cart_id}")
    suspend fun modCartProduct(
        @Path("cart_id") cartId: Int,
        @Query("product_id") productId: Int,
        @Query("quantity") productQuantity: Int,
    ): Response<ModCartProduct>


    @POST("profile/favorites/toggle")
    suspend fun favoritesToggle(
        @Query("model_id") productId: Int,
        @Query("model_name") productType: String
    ): Response<FavoritesToggle>

    @GET("products/{product_id}/rating")
    suspend fun getProductRating(
        @Path("product_id") productId: Int
    ): Response<ProductRatingData>

    @FormUrlEncoded
    @POST("products/{product_id}/rating")
    suspend fun addProductRating(
        @Path("product_id") productId: Int,
        @Field("comment") productComment: String,
        @Field("rating") productRating: Int
    ): Response<AddProductRating>


    @GET("products/{product_id}/simular")
    suspend fun getSimularProducts(
        @Path("product_id") productId: Int
    ): Response<SimularProduct>


    @GET("main/advertisements")
    suspend fun getAdvertisements(
        @Query("page") page: Int = 2
    ): Response<Advertisements>

    @GET("profile/delivery-addresses")
    suspend fun getAllAdresses(
        @Query("page") page: Int = 1,
    ): Response<AdressesData>

    @POST("profile/delivery-addresses")
    suspend fun addAdress(
        @Query("name") adressName: String,
        @Query("city_id") cityId: Int,
        @Query("region") regionName: String,
        @Query("zip_code") zipCode: Int
    ): Response<AddAdress>

    @PUT("profile/delivery-addresses/{delivery_address_id}")
    suspend fun updateAdress(
        @Path("delivery_address_id") addressId: Int,
        @Query("name") adressName: String,
        @Query("city_id") cityId: Int,
        @Query("region") regionName: String,
        @Query("zip_code") zipCode: Int
    ): Response<UpdateAdress>

    @GET("profile/delivery-addresses/{delivery_address_id}")
    suspend fun getAddressById(
        @Path("delivery_address_id") addressId: Int
    ): Response<AdressById>

    @GET("product-filters")
    suspend fun getFilterData(): Response<FilterData>

    @GET("profile/user")
    suspend fun getUser(): Response<UserData>

    @GET("profile/advertisements")
    suspend fun getMyAdvertisements(): Response<MyAdvertisementsData>

    @Headers("Content-Type: application/json")
    @POST("profile/advertisements")
    @JvmSuppressWildcards
    suspend fun addAdvertisement(
        @Body request: Map<String, Any>
    ): Response<AddAdvertisement>

    @DELETE("profile/advertisements/{advertisement_id}")
    suspend fun deleteAddvertisement(
        @Path("advertisement_id") advertisement_id: Int
    ): Response<DeleteAdvertisementData>

    @GET("products")
    @JvmSuppressWildcards
    suspend fun getFilteredProducts(
        @QueryMap filterData: Map<String, Any>
    ): Response<FilteredProducts>

    @Multipart
    @POST("upload-file")
    suspend fun uploadFile(
        @Part files: MultipartBody.Part
    ): Response<UploadFiles>

    @GET("attributes/{category_id}")
    suspend fun getAttributesById(
        @Path("category_id") category_id: Int
    ): Response<AttributesById>


    companion object Constants {
        const val AuthorizationToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwaG9uZSI6Ijk5Mjc3NzAwMDQ0NCIsImV4cCI6MTY5MDE3OTUwMn0.29WL4Ah8rvGuKl3nFout8HFvzTHvNYG4BsaisOV-phY"
    }

}