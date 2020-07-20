class UserPointsBalanceResourceIT {

    @BeforeEach
    public void initTest() {
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(new UsernamePasswordAuthenticationToken(RANDOM_LOGIN, RANDOM_PASSWORD));
        SecurityContextHolder.setContext(securityContext);
    }
}