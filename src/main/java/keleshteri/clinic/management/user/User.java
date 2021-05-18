package keleshteri.clinic.management.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;


@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "users")
public class User  implements UserDetails {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NaturalId
    @Column(unique = true)
    @NotBlank(message = "User email cannot be null")
    @Email
    private String email;

    @Column(unique = true)
    @NotBlank(message = "username cannot be null")
    private String username;

    @NotNull(message = "Password cannot be null")
    private String password;

    @Column(name = "IS_EMAIL_VERIFIED", nullable = false)
    private Boolean isEmailVerified;

    @Column(name = "is_active", nullable = false)
    private Boolean active;

    @Column(name = "is_account_expired", nullable = false)
    private Boolean accountExpired;

    @Column(name = "is_account_locked", nullable = false)
    private Boolean accountLocked;


    //create user_has_roles
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_has_roles", joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Set<Role> roles = new HashSet<>();

    //user_has_permissions
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_has_permissions", joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "permission_id", referencedColumnName = "id")})
    private Set<Permission> permissions = new HashSet<>();

    //----


    public User(String username, String password, Set<Role> roles) {
        this.username = username;
        this.email = username+"@m.com";
        this.password = password;
        this.roles = roles;
        this.accountExpired=true;
        this.accountLocked=true;
        this.active=true;
        this.isEmailVerified=true;
    }


    //UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //get list roles
        Set<SimpleGrantedAuthority> RolesList = this.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" +role.getName()))
                .collect(Collectors.toSet());

        //user_has_permissions
        Set<SimpleGrantedAuthority> userHasPermissions = this.getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                .collect(Collectors.toSet());

        //roleHasPermissions
        Set<Permission> roleHasPermissions = new HashSet<Permission>();
        //get roleHasPermissions
        this.getRoles().forEach(new Consumer<Role>() {
            @Override
            public void accept(Role role) {
                roleHasPermissions.addAll(role.getPermissions().stream().map(permission -> permission).collect(Collectors.toSet()));
                System.out.println(roleHasPermissions);
            }
        });
        //
        //permissionsList
        Set<SimpleGrantedAuthority> permissionsList = roleHasPermissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                .collect(Collectors.toSet());
        RolesList.addAll(permissionsList);
        RolesList.addAll(userHasPermissions);

        return RolesList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !accountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active & isEmailVerified;
    }
}
