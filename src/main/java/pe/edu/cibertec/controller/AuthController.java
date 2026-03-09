	package pe.edu.cibertec.controller;
	
	import java.time.LocalDateTime;
	import java.util.Map;
	
	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	import org.springframework.security.core.userdetails.UserDetails;
	import org.springframework.security.core.userdetails.UserDetailsService;
	import org.springframework.security.crypto.password.PasswordEncoder;
	import org.springframework.web.bind.annotation.*;
	
	import pe.edu.cibertec.dto.LoginRequest;
	import pe.edu.cibertec.dto.LoginResponse;
	import pe.edu.cibertec.entity.Rol;
	import pe.edu.cibertec.entity.Usuario;
	import pe.edu.cibertec.repository.RolRepository;
	import pe.edu.cibertec.repository.UsuarioRepository;
	import pe.edu.cibertec.config.JwtUtil;
	
	@RestController
	@RequestMapping("/api/auth")
	@CrossOrigin(origins = "http://localhost:4200")
	public class AuthController {
	
	    private final UsuarioRepository usuarioRepository;
	    private final RolRepository rolRepository;
	    private final PasswordEncoder passwordEncoder;
	    private final JwtUtil jwtUtil;
	    private final UserDetailsService userDetailsService;
	
	    public AuthController(
	            UsuarioRepository usuarioRepository,
	            RolRepository rolRepository,
	            PasswordEncoder passwordEncoder,
	            JwtUtil jwtUtil,
	            UserDetailsService userDetailsService) {
	
	        this.usuarioRepository = usuarioRepository;
	        this.rolRepository = rolRepository;
	        this.passwordEncoder = passwordEncoder;
	        this.jwtUtil = jwtUtil;
	        this.userDetailsService = userDetailsService;
	    }
	
	    // ✅ REGISTRO
	    @PostMapping("/registro")
	    public ResponseEntity<?> registrarCliente(@RequestBody Usuario usuario) {
	
	        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
	            return ResponseEntity
	                    .status(HttpStatus.CONFLICT)
	                    .body(Map.of("mensaje", "El email ya está registrado"));
	        }
	
	        Rol rolCliente = rolRepository.findByNombreRol("COMPRADOR")
	                .orElseThrow(() -> new RuntimeException("Rol COMPRADOR no existe"));
	
	        Usuario nuevo = new Usuario();
	        nuevo.setNombre(usuario.getNombre());
	        nuevo.setApellido(usuario.getApellido());
	        nuevo.setEmail(usuario.getEmail());
	        nuevo.setPassword(passwordEncoder.encode(usuario.getPassword()));
	        nuevo.setRol(rolCliente);
	        nuevo.setBloqueado(false);
	        nuevo.setFechaRegistro(LocalDateTime.now());
	
	        usuarioRepository.save(nuevo);
	
	        return ResponseEntity
	                .status(HttpStatus.CREATED)
	                .body(Map.of("mensaje", "Usuario registrado correctamente"));
	    }
	
	    // ✅ LOGIN REAL CON JWT
	    @PostMapping("/login")
	    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
	
	        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
	                .orElseThrow(() -> new RuntimeException("Credenciales incorrectas"));
	
	        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
	            return ResponseEntity.badRequest().body("Credenciales incorrectas");
	        }
	
	        UserDetails userDetails =
	                userDetailsService.loadUserByUsername(usuario.getEmail());
	
	        String token = jwtUtil.generateToken(userDetails);
	
	        String rol = usuario.getRol().getNombreRol();
	
	        return ResponseEntity.ok(new LoginResponse(token, rol, usuario.getIdUsuario()));	    }
	    
	    
	}
