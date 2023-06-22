@file:Suppress("TooGenericExceptionCaught", "SwallowedException")
package dev.tavieto.movielibrary.core.util.enums

enum class States(val uf: String = "", val value: String = "") {
    NONE,
    ACRE(
        uf = "AC",
        value = "Acre"
    ),
    ALAGOAS(
        uf = "AL",
        value = "Alagoas"
    ),
    AMAPA(
        uf = "AP",
        value = "Amapá"
    ),
    AMAZONAS(
        uf = "AM",
        value = "Amazonas"
    ),
    BAHIA(
        uf = "BA",
        value = "Bahia"
    ),
    CEARA(
        uf = "CE",
        value = "Ceará"
    ),
    DISTRITO_FEDERAL(
        uf = "DF",
        value = "Distrito Federal"
    ),
    ESPIRITO_SANTO(
        uf = "ES",
        value = "Espírito Santo"
    ),
    GOIAS(
        uf = "GO",
        value = "Goiás"
    ),
    MARANHAO(
        uf = "MA",
        value = "Maranhão"
    ),
    MATO_GROSSO(
        uf = "MT",
        value = "Mato Grosso"
    ),
    MATO_GROSSO_DO_SUL(
        uf = "MS",
        value = "Mato Grosso do Sul"
    ),
    MINAS_GERAIS(
        uf = "MG",
        value = "Minas Gerais"
    ),
    PARA(
        uf = "PA",
        value = "Pará"
    ),
    PARAIBA(
        uf = "PB",
        value = "Paraíba"
    ),
    PARANA(
        uf = "PR",
        value = "Paraná"
    ),
    PERNAMBUCO(
        uf = "PE",
        value = "Pernambuco"
    ),
    PIAUI(
        uf = "PI",
        value = "Piauí"
    ),
    RIO_DE_JANEIRO(
        uf = "RJ",
        value = "Rio de Janeiro"
    ),
    RIO_GRANDE_DO_NORTE(
        uf = "RN",
        value = "Rio Grande do Norte"
    ),
    RIO_GRANDE_DO_SUL(
        uf = "RS",
        value = "Rio Grande do Sul"
    ),
    RONDONIA(
        uf = "RO",
        value = "Rôndonia"
    ),
    RORAIMA(
        uf = "RR",
        value = "Roraima"
    ),
    SANTA_CATARINA(
        uf = "SC",
        value = "Santa Catarina"
    ),
    SAO_PAULO(
        uf = "SP",
        value = "São Paulo"
    ),
    SERGIPE(
        uf = "SE",
        value = "Sergipe"
    ),
    TOCANTINS(
        uf = "TO",
        value = "Tocantins"
    );

    companion object {
        fun fromUf(uf: String): States = try {
            values().first { it.uf.uppercase() == uf }
        } catch (ufConverterError: Throwable) {
            NONE
        }

        fun fromValue(value: String): States = try {
            values().first { it.value.uppercase() == value }
        } catch (ufConverterError: Throwable) {
            NONE
        }
    }
}
