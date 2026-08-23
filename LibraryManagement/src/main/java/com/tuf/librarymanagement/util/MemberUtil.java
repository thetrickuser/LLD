package com.tuf.librarymanagement.util;

import com.tuf.librarymanagement.domain.member.Role;

public class MemberUtil {

    public static String generateMembershipId(String tenantId, Role role, int existingMemberCount) {
        String prefix = role == Role.LIBRARIAN ? "LIB" : "MEM";
        return tenantId + "/" + prefix + "-" + (existingMemberCount + 1);
    }
}
