package io.github.oliviercailloux.git.common;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

import com.google.common.net.InternetDomainName;

/**
 * The coordinates of a repository constituted of an authority, an owner and a repository name and
 * reachable at the git uri {@code ssh://authority/owner/repo.git}. This format is used by such
 * hosts as <a href="https://github.com/">GitHub</a> or
 * <a href="https://bitbucket.org/">BitBucket</a>.
 * <p>
 * This class also accepts authorities of the form {@code host:port} or with a
 * <a href="https://datatracker.ietf.org/doc/html/rfc3986#section-3.2">userinfo</a> part or using an
 * IP address instead of a domain name.
 */
public class RepositoryCoordinates {
  public static RepositoryCoordinates from(String authority, String owner, String repo) {
    return new RepositoryCoordinates(authority, owner, repo);
  }

  private final String authority;
  private final String owner;
  private final String repo;
  private final GitUri uri;

  private RepositoryCoordinates(String authority, String owner, String repo) {
    this.authority = requireNonNull(authority);
    this.owner = requireNonNull(owner);
    checkArgument(!owner.contains("/"));
    this.repo = requireNonNull(repo);
    checkArgument(!repo.contains("/"));
    uri = GitUri.ssh(authority, "/" + owner + "/" + repo + ".git");
  }

  public String authority() {
    return authority;
  }

  public String owner() {
    return owner;
  }

  public String repositoryName() {
    return repo;
  }

  /**
   * @deprecated This only identifies the entry given an authority, so I doubt that valid use cases
   *             exist for this concept.
   */
  @Deprecated
  public String id() {
    return owner + "/" + repo;
  }

  public GitUri asGitUri() {
    return uri;
  }
}
