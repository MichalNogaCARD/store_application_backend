package com.app.validators;

import com.app.Utilities.CustomRegex;
import com.app.dto.CompanyDTO;
import com.app.exceptions.AppException;
import com.app.model.InfoCodes;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CompanyDtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(CompanyDTO.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CompanyDTO companyDTO = (CompanyDTO) o;
        final String NAME_REGEX = CustomRegex.TEXT_WITH_DIGITS_AND_SPECIALS_REGEX;
        final String USER_INPUT_REGEX = CustomRegex.TEXT_ONLY_REGEX;
        final String NIP_REGEX = "(\\d){10}";
        final String STREET_NUMBER_REGEX = "[a-zA-z\\s\\d]+";
        final String POST_CODE_REGEX = "(\\d){2}-(\\d){3}";

        try {
            if (!companyDTO.getName().matches(NAME_REGEX)) {
                errors.rejectValue("name", "NIEPRAWIDŁOWA NAZWA. DOZWOLONE SĄ TYLKO LITERY.");
            }
            if (!companyDTO.getNameShortcut().matches(NAME_REGEX)) {
                errors.rejectValue("nameShortcut", "NIEPRAWIDŁOWY SKRÓT. DOZWOLONE SĄ TYLKO LITERY.");
            }
            if (!companyDTO.getNip().matches(NIP_REGEX)) {
                errors.rejectValue("nip", "NIEPRAWIŁOWY NIP. DOZWOLONE SĄ TYLKO CYFRY.");
            }
            if (!companyDTO.getStreet().matches(USER_INPUT_REGEX)) {
                errors.rejectValue("street", "NIEPRAWIDŁOWA ULICA. DOZWOLONE SĄ TYLKO LITERY.");
            }
            if (!companyDTO.getStreetNumber().matches(STREET_NUMBER_REGEX)) {
                errors.rejectValue("streetNumber", "NIEPRAWIDŁOWY NUMER ULICY. DOZWOLONE SĄ TYLKO LITERY I CYFRY.");
            }
            if (!companyDTO.getCity().matches(USER_INPUT_REGEX)) {
                errors.rejectValue("city", "NIEPRAWIDŁOWE MIASTO. DOZWOLONE SĄ TYLKO LITERY.");
            }
            if (!companyDTO.getPostCode().matches(POST_CODE_REGEX)) {
                errors.rejectValue("postCode", "NIEPRAWIDŁOWY KOD POCZTOWY. DOZWOLONE SĄ TYLKO CYFRY");
            }
        } catch (Exception e) {
            throw new AppException(InfoCodes.VALIDATION, "CompanyDto");
        }
    }
}