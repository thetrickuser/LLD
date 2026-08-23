package com.tuf.librarymanagement.repository;

import java.util.concurrent.ConcurrentHashMap;

import com.tuf.librarymanagement.domain.member.Member;
import com.tuf.librarymanagement.domain.member.MembershipStatus;
import com.tuf.librarymanagement.domain.member.Role;

public class MemberRepository {

    private ConcurrentHashMap<String, Member> memberStore = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, int[]> tenantMemberCount = new ConcurrentHashMap<>();

    public Member save(Member member) {
        memberStore.put(member.getId().toString(), member);
        tenantMemberCount.computeIfAbsent(member.getTenantId(), k -> new int[]{0});
        if (member.getRole() == Role.LIBRARIAN) {
            tenantMemberCount.get(member.getTenantId())[0]++;
        } else {
            tenantMemberCount.get(member.getTenantId())[1]++;
        }
        return member;
    }

    public Member findById(String memberId) {
        return memberStore.get(memberId);
    }

    public int getMemberCountByRole(String tenantId, Role role) {
        int[] counts = tenantMemberCount.get(tenantId);
        if (counts == null) {
            return 0;
        }
        return role == Role.LIBRARIAN ? counts[0] : counts[1];
    }

    public void blockMember(String memberId) {
        Member member = memberStore.get(memberId);
        if (member != null) {
            member.setStatus(MembershipStatus.BLOCKED);
            memberStore.put(memberId, member);
        }
    }

    public void activateMember(String memberId) {
        Member member = memberStore.get(memberId);
        if (member != null) {
            member.setStatus(MembershipStatus.ACTIVE);
            memberStore.put(memberId, member);
        }
    }

    public void suspendMember(String memberId) {
        Member member = memberStore.get(memberId);
        if (member != null) {
            member.setStatus(MembershipStatus.SUSPENDED);
            memberStore.put(memberId, member);
        }
    }

    public void deactivateMember(String memberId) {
        Member member = memberStore.get(memberId);
        if (member != null) {
            member.setStatus(MembershipStatus.EXPIRED);
            memberStore.put(memberId, member);
        }
    }

    public boolean existsById(String membershipId) {
        return memberStore.containsKey(membershipId);
    }
}
