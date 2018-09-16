package processing;

/**
 * Enum to record the command type of a user's submitted message.
 * Used inSocialCommandProcessor to determine message type.
 */

public enum CommandType { POSTING, READING, FOLLOWING, WALL, INVALID};
