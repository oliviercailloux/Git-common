package io.github.oliviercailloux.git.common;

import java.time.ZonedDateTime;

/**
 * An identifier, that is, a name and an email address, together with a timestamp (with a time
 * zone); of the kind that git associates to author and committer for each commit.
 */
public record IdStamp (String name, String email, ZonedDateTime timestamp) {

}
