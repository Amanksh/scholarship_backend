package com.nsp.portal.enums;

/**
 * Enum representing the different roles a user can have in the system.
 * Each role has specific permissions and access levels.
 */
public enum Role {
    STUDENT,           // Can apply for scholarships and manage profile
    INSTITUTE,         // Can verify student applications
    STATE_OFFICER,     // Can approve applications at state level
    MINISTRY           // Can grant final approval for scholarships
}
