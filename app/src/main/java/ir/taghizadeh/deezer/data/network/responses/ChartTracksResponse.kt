package ir.taghizadeh.deezer.data.network.responses
import com.google.gson.annotations.SerializedName
import ir.taghizadeh.deezer.data.network.responses.general.Track


data class ChartTracksResponse(
    @SerializedName("data")
    val data: List<Track>,
    @SerializedName("total")
    val total: Int
)



