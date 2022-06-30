package com.ethbek.mezion.inventory.service.services;


import com.ethbek.mezion.inventory.service.models.avro.RoleMap;
import com.ethbek.mezion.inventory.service.permission.AuthenticatedUser;
import com.ethbek.mezion.inventory.service.permission.Role;
import com.ethbek.mezion.inventory.service.permission.SystemRights;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Slf4j
@Service
public class AccessControlService {

    private static final String EXTRACT_ROLE_ERROR_MESSAGE = "Failed to extract user role: {} ";

    private final String extractRole(AuthenticatedUser user, Long organisationalId, String branchId) {
        String organisation = "" + organisationalId;
        return user.getRoles().stream()
                .filter(role -> organisation.equals(role.getOrganisationalId()))
                .filter(role -> branchId.equals(role.getBranchId()))
                .map( Role::getRoleId)
                .findFirst().orElse("");
    }


    public boolean isGeneral(AuthenticatedUser user, Long organisationalId, String branchId) {

        if (Objects.isNull(organisationalId) || Objects.isNull(branchId))
            return false;

        try {
            switch (SystemRights.valueOf(extractRole(user, organisationalId, branchId))) {
                case SYS_ADMIN:
                case ADMIN:
                case SALES:
                case GENERAL:
                    return true;
                default:
                    return false;
            }
        } catch (Exception ex) {
            log.warn(EXTRACT_ROLE_ERROR_MESSAGE, ex.getLocalizedMessage());
        }
        return false;
    }

    public boolean isSales(AuthenticatedUser user, Long organisationalId, String branchId) {

        if (Objects.isNull(organisationalId) || Objects.isNull(branchId))
            return false;

        try {
            switch (SystemRights.valueOf(extractRole(user, organisationalId, branchId))) {
                case SYS_ADMIN:
                case ADMIN:
                case SALES:
                    return true;
                default:
                    return false;
            }
        } catch (Exception ex) {
            log.warn(EXTRACT_ROLE_ERROR_MESSAGE, ex.getLocalizedMessage());
        }
        return false;
    }

    public boolean isOrganisationAdministrator(AuthenticatedUser user, Long organisationalId, String branchId) {

        if (Objects.isNull(organisationalId) || Objects.isNull(branchId))
            return false;

        try {
            switch (SystemRights.valueOf(extractRole(user, organisationalId, branchId))) {
                case SYS_ADMIN:
                case ADMIN:
                    return true;
                default:
                    return false;
            }
        } catch (Exception ex) {
            log.warn(EXTRACT_ROLE_ERROR_MESSAGE, ex.getLocalizedMessage());
        }

        return false;
    }

    public boolean isSystemAdministrator(AuthenticatedUser user, Long organisationalId, String branchId) {
        try {
        if (Objects.isNull(organisationalId) || Objects.isNull(branchId))
            return (SystemRights.valueOf(extractRole(user, organisationalId, branchId)) ).equals(SystemRights.SYS_ADMIN);
        } catch (Exception ex) {
            log.warn(EXTRACT_ROLE_ERROR_MESSAGE, ex.getLocalizedMessage());
        }
        return false;
    }
}
