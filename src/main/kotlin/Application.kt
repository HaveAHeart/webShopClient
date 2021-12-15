import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import model.AuthData
import model.AuthSuccess

//auth/register<AuthData>
//auth/login<AuthData> -> token
const val ip = "26.82.19.20"
const val port = "8080"

suspend fun main() {
    val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    var login = ""
    var pwdHash = ""
    var token = ""
    while (true) {
        println("hello! Want to register or login?(r/l)")
        val mode = readLine()!!
        if (mode != "r" && mode != "l") {
            println("Please, enter only one letter(r/l). Is it so hard?")
            continue
        }
        println("Great! Now, please, provide your credentials.")
        println("login:")
        login = readLine()!!
        println("password:")
        pwdHash = readLine()!!.hashCode().toString(radix = 16)
        val authData = AuthData(login, pwdHash)
        if (mode == "r") {
            println("Great! trying to register you at http://$ip:$port/auth/register...")
            val response: HttpResponse = client.post("http://$ip:$port/auth/register") {
                contentType(ContentType.Application.Json)
                body = authData
            }
            if (response.status == HttpStatusCode.BadRequest) {
                println(response.readText())
                continue
            }
            else {
                println(response.readText())
                continue
            }
        }
        else {
            println("Great! trying to log you in...")
            try {
                val response = client.post<AuthSuccess>("http://$ip:$port/auth/login") {
                    contentType(ContentType.Application.Json)
                    body = AuthData(login, pwdHash)
                }
                token = response.jwtToken
                println(token)
                break
            } catch (e: ClientRequestException) {
                println(e.localizedMessage)
                continue
            }
        }
    }

    println()


    //
}