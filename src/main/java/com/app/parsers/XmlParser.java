package com.app.parsers;

import com.app.Utilities.CustomPaths;
import com.app.dto.CartDTO;
import com.app.dto.CompanyDTODetailsFromFile;
import com.app.dto.ProductDTO;
import com.app.service.FileService;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Component
@NoArgsConstructor
@Data
public class XmlParser {

    private FileService fileService;

    public String generateXmlFileContent(CartDTO cartDTO, Set<ProductDTO> productsInCart) {
        StringBuilder stringBuilder = new StringBuilder();


        System.out.println("**************************************");
        System.out.println("NIP=" + cartDTO.getUserDTO().getCompanyDTO().getNip());
        System.out.println("cartdto=" + cartDTO);
        System.out.println("products=" + productsInCart);
        System.out.println("cmp detail sfile="+CustomPaths.COMPANIES_FILE_PATH);
        System.out.println("saved path=" + CustomPaths.SAVED_ORDERS_PATH);


        try {

            CompanyDTODetailsFromFile companyDTODetailsFromFile =
                    fileService.getCompanyDetailsFromFile(cartDTO.getUserDTO().getCompanyDTO().getNip());


            stringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            stringBuilder.append("<ROOT xmlns=\"http://www.cdn.com.pl/optima/dokument\">");
            stringBuilder.append("<DOKUMENT>");
            stringBuilder.append("<NAGLOWEK>");
            stringBuilder.append("<GENERATOR>Comarch Opt!ma</GENERATOR>");
            stringBuilder.append("<TYP_DOKUMENTU>320</TYP_DOKUMENTU>");
            stringBuilder.append("<RODZAJ_DOKUMENTU>320000</RODZAJ_DOKUMENTU>");
            stringBuilder.append("<FV_MARZA>0</FV_MARZA>");
            stringBuilder.append("<FV_MARZA_RODZAJ>0</FV_MARZA_RODZAJ>");
            stringBuilder.append("<NUMER_PELNY>FPF/99999/2019</NUMER_PELNY>");
            stringBuilder.append("<DATA_DOKUMENTU>");
            stringBuilder.append(LocalDate.now());
            stringBuilder.append("</DATA_DOKUMENTU>");
            stringBuilder.append("<DATA_WYSTAWIENIA>");
            stringBuilder.append(LocalDate.now());
            stringBuilder.append("</DATA_WYSTAWIENIA>");
            stringBuilder.append("<DATA_OPERACJI>");
            stringBuilder.append(LocalDate.now());
            stringBuilder.append("</DATA_OPERACJI>");
            stringBuilder.append("<TERMIN_ZWROTU_KAUCJI>2019-12-31</TERMIN_ZWROTU_KAUCJI>");
            stringBuilder.append("<KOREKTA>0</KOREKTA>");
            stringBuilder.append("<DETAL>0</DETAL>");
            stringBuilder.append("<TYP_NETTO_BRUTTO>1</TYP_NETTO_BRUTTO>");
            stringBuilder.append("<RABAT>0.00</RABAT>");
            stringBuilder.append("<OPIS>");
            stringBuilder.append(cartDTO.getDeliveryAddressDTO());
            stringBuilder.append("</OPIS>");
            stringBuilder.append("<PLATNIK>");
            stringBuilder.append("<KOD>");
            stringBuilder.append(companyDTODetailsFromFile.getCode());
            stringBuilder.append("</KOD>");
            stringBuilder.append("<NIP_KRAJ>PL</NIP_KRAJ>");
            stringBuilder.append("<NIP>");
            stringBuilder.append(cartDTO.getUserDTO().getCompanyDTO().getNip());
            stringBuilder.append("</NIP>");
            stringBuilder.append("<GLN/>");
            stringBuilder.append("<NAZWA>");
            stringBuilder.append(companyDTODetailsFromFile.getName());
            stringBuilder.append("</NAZWA>");
            stringBuilder.append("<ADRES>");
            stringBuilder.append("<KOD_POCZTOWY>");
            stringBuilder.append(companyDTODetailsFromFile.getPostalCode());
            stringBuilder.append("</KOD_POCZTOWY>");
            stringBuilder.append("<MIASTO>");
            stringBuilder.append(companyDTODetailsFromFile.getCity());
            stringBuilder.append("</MIASTO>");
            stringBuilder.append("<ULICA>");
            stringBuilder.append(companyDTODetailsFromFile.getStreet());
            stringBuilder.append("</ULICA>");
            stringBuilder.append("<KRAJ>Polska</KRAJ>");
            stringBuilder.append("</ADRES>");
            stringBuilder.append("</PLATNIK>");
            stringBuilder.append("<ODBIORCA>");
            stringBuilder.append("<KOD>");
            stringBuilder.append(companyDTODetailsFromFile.getCode());
            stringBuilder.append("/<KOD>");
            stringBuilder.append("<NIP_KRAJ>PL</NIP_KRAJ>");
            stringBuilder.append("<NIP>");
            stringBuilder.append(companyDTODetailsFromFile.getNip());
            stringBuilder.append("/<NIP>");
            stringBuilder.append("<GLN/>");
            stringBuilder.append("<NAZWA>");
            stringBuilder.append(companyDTODetailsFromFile.getName());
            stringBuilder.append("/<NAZWA>");
            stringBuilder.append("<ADRES>");
            stringBuilder.append("<KOD_POCZTOWY>");
            stringBuilder.append(companyDTODetailsFromFile.getPostalCode());
            stringBuilder.append("/<KOD_POCZTOWY>");
            stringBuilder.append("<MIASTO>");
            stringBuilder.append(companyDTODetailsFromFile.getCity());
            stringBuilder.append("/<MIASTO>");
            stringBuilder.append("<ULICA>");
            stringBuilder.append(companyDTODetailsFromFile.getStreet());
            stringBuilder.append("/<ULICA>");
            stringBuilder.append("<KRAJ>Polska</KRAJ>");
            stringBuilder.append("</ADRES>");
            stringBuilder.append("</ODBIORCA>");
            stringBuilder.append("<SPRZEDAWCA>");
            stringBuilder.append("<NIP_KRAJ>PL</NIP_KRAJ>");
            stringBuilder.append("<NIP>6770062135</NIP>");
            stringBuilder.append("<GLN>0000000000000</GLN>");
            stringBuilder.append("<NAZWA>FIRMA HANDLOWA PRIMA ZDZISŁAW NOGA</NAZWA>");
            stringBuilder.append("<ADRES>");
            stringBuilder.append("<KOD_POCZTOWY>30-740</KOD_POCZTOWY>");
            stringBuilder.append("<MIASTO>KRAKÓW</MIASTO>");
            stringBuilder.append("<ULICA>UL.PÓŁŁANKI 31G</ULICA>");
            stringBuilder.append("<KRAJ>POLSKA</KRAJ>");
            stringBuilder.append("</ADRES>");
            stringBuilder.append("<NUMER_KONTA_BANKOWEGO>PL48124045331111000054227712</NUMER_KONTA_BANKOWEGO>");
            stringBuilder.append("<NAZWA_BANKU>Bank PEKAO SA 48 1240 4533 1111 0000 5422 7712</NAZWA_BANKU>");
            stringBuilder.append("</SPRZEDAWCA>");
            stringBuilder.append("<KATEGORIA>");
            stringBuilder.append("<KOD/>");
            stringBuilder.append("<OPIS/>");
            stringBuilder.append("</KATEGORIA>");
            stringBuilder.append("<PLATNOSC>");
            stringBuilder.append("<FORMA>przelew</FORMA>");
            stringBuilder.append("<TERMIN>");
            stringBuilder.append(LocalDate.now().plusDays(cartDTO.getUserDTO().getCompanyDTO().getPaymentInDays()));
            stringBuilder.append("</TERMIN>");
            stringBuilder.append("</PLATNOSC>");
            stringBuilder.append("<WALUTA>");
            stringBuilder.append("<SYMBOL>PLN</SYMBOL>");
            stringBuilder.append("<KURS_L>1.00</KURS_L>");
            stringBuilder.append("<KURS_M>1</KURS_M>");
            stringBuilder.append("<PLAT_WAL_OD_PLN>0</PLAT_WAL_OD_PLN>");
            stringBuilder.append("<KURS_NUMER>3</KURS_NUMER>");
            stringBuilder.append("<KURS_DATA>2019-07-23</KURS_DATA>");
            stringBuilder.append("</WALUTA>");
            stringBuilder.append("<KWOTY>");
            stringBuilder.append("<RAZEM_NETTO_WAL>");
            stringBuilder.append(cartDTO.getTotalNetValue());
            stringBuilder.append("</RAZEM_NETTO_WAL>");
            stringBuilder.append("<RAZEM_NETTO>");
            stringBuilder.append(cartDTO.getTotalNetValue());
            stringBuilder.append("</RAZEM_NETTO>");
            stringBuilder.append("<RAZEM_BRUTTO>");
            stringBuilder.append(cartDTO.getTotalGrossValue());
            stringBuilder.append("</RAZEM_BRUTTO>");
            stringBuilder.append("<RAZEM_VAT>");
            stringBuilder.append(cartDTO.getTotalVatValue());
            stringBuilder.append("</RAZEM_VAT>");
            stringBuilder.append("</KWOTY>");
            stringBuilder.append("<MAGAZYN_ZRODLOWY>MAGAZYN_1</MAGAZYN_ZRODLOWY>");
            stringBuilder.append("<MAGAZYN_DOCELOWY/>");
            stringBuilder.append("<KAUCJE_PLATNOSCI>0</KAUCJE_PLATNOSCI>");
            stringBuilder.append("<BLOKADA_PLATNOSCI>1</BLOKADA_PLATNOSCI>");
            stringBuilder.append("<VAT_DLA_DOK_WAL>0</VAT_DLA_DOK_WAL>");
            stringBuilder.append("<TRYB_NETTO_VAT>0</TRYB_NETTO_VAT>");
            stringBuilder.append("</NAGLOWEK>");
            stringBuilder.append("<POZYCJE>");

            productsInCart
                    .forEach(product -> {
                        stringBuilder.append("<POZYCJA>");
                        stringBuilder.append("<TOWAR>");
                        stringBuilder.append("<KOD>");
                        stringBuilder.append(product.getOptimaCode());
                        stringBuilder.append("</KOD>");
                        stringBuilder.append("<NAZWA>");
                        stringBuilder.append(product.getName());
                        stringBuilder.append("</NAZWA>");
                        stringBuilder.append("<OPIS/>");
                        stringBuilder.append("<EAN/>");
                        stringBuilder.append("<SWW/>");
                        stringBuilder.append("<NUMER_KATALOGOWY/>");
                        stringBuilder.append("</TOWAR>");
                        stringBuilder.append("<STAWKA_VAT>");
                        stringBuilder.append("<STAWKA>");
                        stringBuilder.append(product.getVat());
                        stringBuilder.append("</STAWKA>");
                        stringBuilder.append("<FLAGA>2</FLAGA>"); // TODO: 2020-02-10 od czego jest ta flaga ???
                        stringBuilder.append("<ZRODLOWA>0.00</ZRODLOWA>");
                        stringBuilder.append("</STAWKA_VAT>");
                        stringBuilder.append("<CENY>");
                        stringBuilder.append("<CENAZCZTEREMAMIEJSCAMI>0</CENAZCZTEREMAMIEJSCAMI>");
                        stringBuilder.append("<POCZATKOWA_WAL_CENNIKA>");
                        stringBuilder.append(product.getNettPrice());
                        stringBuilder.append("</POCZATKOWA_WAL_CENNIKA>");
                        stringBuilder.append("<POCZATKOWA_WAL_DOKUMENTU>");
                        stringBuilder.append(product.getNettPrice());
                        stringBuilder.append("</POCZATKOWA_WAL_DOKUMENTU>");
                        stringBuilder.append("<PO_RABACIE_WAL_CENNIKA>");
                        stringBuilder.append(product.getNettPrice());
                        stringBuilder.append("</PO_RABACIE_WAL_CENNIKA>");
                        stringBuilder.append("<PO_RABACIE_PLN>");
                        stringBuilder.append(product.getNettPrice());
                        stringBuilder.append("</PO_RABACIE_PLN>");
                        stringBuilder.append("<PO_RABACIE_WAL_DOKUMENTU>");
                        stringBuilder.append(product.getNettPrice());
                        stringBuilder.append("</PO_RABACIE_WAL_DOKUMENTU>");
                        stringBuilder.append("</CENY>");
                        stringBuilder.append("<WALUTA>");
                        stringBuilder.append("<SYMBOL>PLN</SYMBOL>");
                        stringBuilder.append("<KURS_L>1.00</KURS_L>");
                        stringBuilder.append("<KURS_M>1</KURS_M>");
                        stringBuilder.append("</WALUTA>");
                        stringBuilder.append("<RABAT>0.00</RABAT>");
                        stringBuilder.append("<WARTOSC_NETTO>");
                        stringBuilder.append(product.getNettPrice().multiply(BigDecimal.valueOf(product.getQuantity())));
                        stringBuilder.append("</WARTOSC_NETTO>");
                        stringBuilder.append("<WARTOSC_BRUTTO>");
                        stringBuilder.append(product.getGrossPrice().multiply(BigDecimal.valueOf(product.getQuantity())));
                        stringBuilder.append("</WARTOSC_BRUTTO>");
                        stringBuilder.append("<WARTOSC_NETTO_WAL>");
                        stringBuilder.append(product.getNettPrice().multiply(BigDecimal.valueOf(product.getQuantity())));
                        stringBuilder.append("</WARTOSC_NETTO_WAL>");
                        stringBuilder.append("<WARTOSC_BRUTTO_WAL>");
                        stringBuilder.append(product.getGrossPrice().multiply(BigDecimal.valueOf(product.getQuantity())));
                        stringBuilder.append("</WARTOSC_BRUTTO_WAL>");
                        stringBuilder.append("<ILOSC>");
                        stringBuilder.append(product.getQuantity());
                        stringBuilder.append("</ILOSC>");
                        stringBuilder.append("<JM>");
                        stringBuilder.append("</JM>");
                        stringBuilder.append("<JM_CALKOWITE>0.00</JM_CALKOWITE>");
                        stringBuilder.append("<JM_ZLOZONA>");
                        stringBuilder.append("<JMZ>");
                        stringBuilder.append("</JMZ>");
                        stringBuilder.append("<JM_PRZELICZNIK_L>1.00</JM_PRZELICZNIK_L>");
                        stringBuilder.append("<JM_PRZELICZNIK_M>1</JM_PRZELICZNIK_M>");
                        stringBuilder.append("</JM_ZLOZONA>");
                        stringBuilder.append("</POZYCJA>");
                    });

            stringBuilder.append("</POZYCJE>");
            stringBuilder.append("<KAUCJE/>");
            stringBuilder.append("<PLATNOSCI/>");
            stringBuilder.append("<PLATNOSCI_KAUCJE/>");
            stringBuilder.append("<TABELKA_VAT>");
            stringBuilder.append("<LINIA_VAT>");
            stringBuilder.append("<STAWKA_VAT>");
            stringBuilder.append("<STAWKA>23.00</STAWKA>");
            stringBuilder.append("<FLAGA>2</FLAGA>");
            stringBuilder.append("<ZRODLOWA>0.00</ZRODLOWA>");
            stringBuilder.append("</STAWKA_VAT>");
            stringBuilder.append("<NETTO>");
            stringBuilder.append(cartDTO.getTotalNetValue());
            stringBuilder.append("</NETTO>");
            stringBuilder.append("<VAT>");
            stringBuilder.append(cartDTO.getTotalVatValue());
            stringBuilder.append("</VAT>");
            stringBuilder.append("<BRUTTO>");
            stringBuilder.append(cartDTO.getTotalGrossValue());
            stringBuilder.append("</BRUTTO>");
            stringBuilder.append("<NETTO_WAL>");
            stringBuilder.append(cartDTO.getTotalNetValue());
            stringBuilder.append("</NETTO_WAL>");
            stringBuilder.append("<VAT_WAL>");
            stringBuilder.append(cartDTO.getTotalVatValue());
            stringBuilder.append("</VAT_WAL>");
            stringBuilder.append("<BRUTTO_WAL>");
            stringBuilder.append(cartDTO.getTotalGrossValue());
            stringBuilder.append("</BRUTTO_WAL>");
            stringBuilder.append("</LINIA_VAT>");
            stringBuilder.append("</TABELKA_VAT>");
            stringBuilder.append("<ATRYBUTY/>");
            stringBuilder.append("</DOKUMENT>");
            stringBuilder.append("</ROOT>");


        } catch (Exception e) {
            System.out.println("________________________________________________________");
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }
}