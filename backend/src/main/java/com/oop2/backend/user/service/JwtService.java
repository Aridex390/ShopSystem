package com.oop2.backend.user.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * This is a service class. This class handle
 * the manipulation and generation of the JWT Toke.
 *
 * @author Florian Reining
 * @version 1.0
 */
@Service
public class JwtService {
    /** this secret key is used to decrypt and encrypt the jwt token */
    private static final String SECRET_KEY = "d8ad514f1925f4b07cda393c7857d83001968cbb71bbfe8e62db3b8e6c278dba5a2fb1bd4c2f7d7b62c22282758dacdb893eb2305d4b554bb8e661d97bbe0d219e231a8315bb8a18d763e9ece56f38ea7f8e15544f6ea64f57bf7a5ca8a167a4c0594430d26f42c1f2777cf07ca7fc4f44bb51eb3e944763a96bc891b99f9fdc684741bd9fa849079f347780be69eda2786d78350644118c9c4bb5d38a7541191356172deee1a26c3ef93ca9721211ce908f561fb36c03ec06194740e9b41986e251d1438c3a8c8381219326ed962f6e38233cdedc8eca6d591418eb7b12524817f783bb7b9aab1a378f3c1bee3e0a4790375779f9e60575aa80d5a7a546eab021bca615b365e82db4b93931055bbcd74e36b003fa231893e5957a4cf0f812bb5dcc3ccdaa4f6990e286617a9af8c2a1d9097efba54e17c92882c435dbbb95af87e7f55a55cbf4bfebe762ace4e18e9b683d6b73b9f4cff2d7a8ba7e6a29451b2d084915debbb822a304076f78496216ea226c07401aa6edb868f6ba890e40feb229d3b5002d035b70de1ec30f0da0cd63d6f5705fda79e98197fbb9c646fd59193ddaa628ee7e193dafcd27cbd1430a53a35999c208186491c91f92b41a74cb045d9f1489f27d7413141245aa2c2cc37650d8c0b5ac6d0ea0e63fbb3f30c02149f22f48ed50c2b53b74536ac24da43765838660244e53c0f73b3f9dad458fbb950930b4f5ea4636c8ee50ef2c8432f8d0d656d9bfc9a342be258360923eafc784925df6720d99460d90633b8f24199ddedc2773d1cc3b9eb33bdf6eb89cae3499ea90ab9b761184d4c0d3f6db11e6fc1df8001f6541994f3987f131ef81e614db880529f77baa19ca568c61553487e8f912e13325fb9b228ed38af0773f62a257df171cd051968a7237b1b205a290351e3fbbd2615d180aa39fd304d41e1471159fc62e004fbc65c505a32b37e3c2d35ab192008c35578be8e93380cad1592d706cf359649fa679bfc57bd31a2a0a1e6236b46ecf54e4e9073dfc4ccdd03e4ea4a8449d4265edec647b87de1cbb452bfcb3bbe85d1bf312089d6bc5927388e3c8d3f7bb6fd1e5cb35d75897907505dbcda4b0acf58fae8901726296aa9929cc56fa3ca44c216d5aeb73aaefb3f9144bb19fa93227b413b2a6aaf35164e457c18a78805359d8c9eb883a3a15c512733bd2599ab001a2b588d62f4d5079030986803cb281ce5bddb84faf322f15f67336def901325582838a902f1decae9a7d977f075109bb05379c97515858e24431267d1159f294421661482def5531d05e87b31b18f7233717f0c22289e8cd20197d31fe8afec60f0f89187c936018ff10764160c593d775747b94b94aed8d9896aebdf5fb08c545915e612be6ed9be1ccd6ca4b8728ad5a0dd67832fa07f98252c597ffaa8eb7f72db2cfe4edde4bacf8791f4084d167cf2e1bde3984dfd77d361e7f8255bb4ad6555210c22fe04259a80c5d294334e1046d74da1c0fef143d87649ccc08a179c9d5b3f86f7071eebfa5229f3c925063146e7ec64d5865b5cb558e493636712a067b34136a8280ff0f7e922b5421729ec2baba5dbe6368eea158c85a30b3f064dd6b82f0ea782a587365b0c11a22260e6c04d17b13c1d7d5a645811fbba965e4705ba33759c61b60888266079a78ff4bc8bcb040f87378d79896856646f21dff459541ef63a326c9f53e3ed62bf07cb9e897e9d69a9887a3223c2816493c1c025d02b2e0724785835ab4da92d41c0746afd490542e4df18f911dbe7ec6ee0addcb6faecd3c5e21c2a2dc2d2a9a79b747299229cae05f9de87c5ac9b800a00a387f939bf9204646ed1492872478b686353c883b44e600a9a29276884a9c1842518812db557514c61888e43f941e25933b5b83817eba210dd4bb4c5a5281d202a7017f462cf47b11f05ccdd80af67fb7fd515368848d758e4aef5695c351dc6f89211478f38914e356b325eef3512443d7a7795cecf0d469099543dfaa6da586f784bc5d23dfbf13c63e831c66c6d9ac7b84274a27921664fa4507aa4235bde234c3926e7bdd075045f10c0b6af7475e7b606934e5a879f034a7152b05d011af9a5524cf373c3105c0e9fb3c3e7da7b9d6aaaf4d93088e995eb2e03adb1b41f3733561942c60e5cf5b1dd100ee0584696967917d29e10016517ab259bf7dd1e57402087282815a1d21fff8c12414941cd980ad65e82d1b7b0df0e4c9aa38fbcb57f59ade19c58569fc47357544fdf61bc639ec05290210d7c2c4a6bac0420bac6f7dd9123ba156c94ea5aa512a60b5ae738974f09289c934d9e2e0c18a799898399bb2d9c04eb433d5da97bb0e42d7385a7ae7ff2611a039022f8af577a2b79ec36440a37b265e79c15071fd3c4311ff343662f5e91bcaeeaea0f756424f5d6029b38c304d9913d17f455a1e0f6fbf3f8b6158f20c9cbab4a1c50a10e44a5f460faf2f261713ca2fb184e9de705a31396f449c88b39c3df8af1cbd0b62c406913ce588ee3d6096818db256c229ecb0e6323e5d2558dc6ffe9f031bf3f55f33237fd9e1e6137660c518136e56d3f69157ab62d415fc4f51ed29c3eecdde5245db58b2ec80261a13c7ebc9f742bb237a9cc7b89ab8d002460718058f31f8a2549d7745d3c69b8dfc03d097eb9437e5a66c737f3d21627377fc70b83743c7835b9cb0d120ae6cb4cdf421ab000911205bdc53be68fbeb1a71efc633d0045f690f30bf4c5fb1cfb8c809b6df2c5ca7965a103c9544d8473e43b167d22b9a0708bc4e36bf61652962912dac2bf82f898965ec07f444a648f81db2a494013094f15271201f0a67a8a4bd732b9f9b368c9aa38f56a87cbba8ffddcc3478614d0a9faf5844c1be943370a38704c26124e293a6c2a97775c85ae02c1497e220ffaa0f4b8b30fb26c2dcb969235af4ec02b1f1ba379e87df36d9c3cf3e46fab8bb3563c12268b2b8d8fb8913e4a05a798e7f1e6188b8536a7892a86050e6d9a563520ffb1df9e1a221041117c043689dc6386d005cd8cd350bbb3cfc48e9ea34c6762314e7e807c0c144e2c07d48bb7bc022c295cee1e051a9514feafb5303341ed6bbf4cf5d128728f88f42b4c447c5cfe67bcc7c874d7a22c881a2ef5af663d428c9db0a0ea72d32e94bce8df95c3880ed5d33b2a2b39ea546b03ca3fabf1426bacc992f1ac98717c1ef97a9a7c4a169d2b3c158861fc09eefa7d274ae9740f374cb555f25851a76772083c91035df9b6fc43ef48b091d72ac32233602e1c683073a2cf874d9cd9133e9843cdad424f0b814aebd00008b12a961d15298c54459c8386309410398d55430cc3f74cc7cefc3923c8bc32a8f55532c1f3bd2a3a56b2f70532bd4c9b66c9b393804b68e4ebfdc82fd9e3ab84c863a0523b3c58f2b88a7d89f255f6bd0c128fd3af5a0b4b4014bb3a1aa98c2a8505a2963d4caa7433d961ba86af07708860f9a6863fa955835a56094508ac470a1de61ef6e51782f3b5ac628b562f165e21c81a034f6eecd0431753d8632f95ecba73b0b46fe273b2c77aa2566cd8a100f7b640bc27015d3950437f44c0c9074085ca26ffff3d14c715766404a7750c7f318e8c62738cf3f67f1007b2c00d73f6fd3891dbee425c3b4a2e5d8f596747fc3048b3855c6c8e22b16462860e917ac4f9b15b9a87fa8a299c77bac089f298a9c8faeecde8c1016995adf555c516784c5f20ce02fdc6f5587a08d8cd79b797b195f2a993d6d0edc69af1937c469cf1cd821a02dc8ab4a9b7baf76b244c522fc6e0ebcf259e79240b38e3dc1f23ca65aa675bda6b21efe69f986f6ebd111f7fd7f2b9e1c4e852a5937bdc1817d8537145a6a65ad0130e710b9b241cb78eed29d873af36f6908a0c6b910412038a7281940b237c2b44054c6b9053aa91026d1e269df86c9a5e459b128fda2b4e26f4ffe7c4c69deb57a869b44dac118c98c8bc9020f7aeb8a24b4a1acfaa8fe111e90229f4a9296331f1b06866d82fd59219993563287cad8167f744269f8d47cbf711bc31f6cb1657da21d6d288ef8f1af9985323cdd9b8e5603847dd7d802fad69e4773240e0ed436948512a546de51580e9134755c8f433b215578425f1ec36731aa0211c8e8ef30d7aca7b5258d17adf6956f80ccf2e0710a65788ed960092309122ea9cf6f58a6468d068a1956b1be35caf5d9581f50c8cdf9319db54642147c0490b240a13c5b1d415a5619c307c963f33218f3775ef2d56848a7ad082f4f65e81904820ffdf52c1432e4f47fd5e93e9291a35a71e018cf3de2e6f832f21a3de8cc7493a5186a6203ae0c879138f7b00dfa40f86653b7bc79c86c7d4414a34ee8b29d50f824c7858afe3a7dfce9fa8750b1a3a61835a515d2c0174cb4fc34dac3bcdd7cfa33f898ca0b411af6dc35577bc7a20cadc9b363ff3db9b27ab194cf53755330864694ce1beaf9670e1bde1c04d1046d9a7c063ffabaa39bcb17121c056ea8286baae75b70571a59697ff84ace87e56653749927b17520a773e495a1e983f45cbe30d214d390780c07c8c094d908c2197854441c6f81e3e8a4033bcdea5761dead365a7e9c4d6666efc48892f8a9ee8284a607d100972a14629bf63c83aee8924cbe59e224cda74f1b446024e3e4028b4694e321cc79ed31ccaf078faff2cf5a82da96eb05885f8d791fd546b9bc92c9bfa4f060f05ee8c84d8c0857b2ba6d959c073d98ce28a44b34b41797d573faf7fc514b75d0cb95e5c1db0fdee85b55331fd9f7a06308fb1e4e8abe14ffc972fb496fb912639d080ac600a8f18b1921f092851a1d75fbd56b4fb61e42c245e6bb00e71e4fdefcb7198c46b21ec1d75306116be789911ac063acedd8a4785076429992f2fdfc3e71bed56303bd788485820fdb00638e77cff434f9fff386dfaff0d897b45d6374dca8e186d108bda5c9c1618a7b6d6fcef384f27725e4fb0857e6f0486f330f5a2d39013b1866278989622173dad40df05b68a5e4597b82c88c5053210556cee043be88344b5e5b0573366c38a929a32b8aaa814877e8cdb0be705ba308a00b4cccf76bf5a037da9acd8bbc8690fdc5d3f4b893a74a29ed43ef0435a8211ba7362910462aa2ed619f248c592c706061ab05e0dac61df3957235951db81d0a019137e8bddf6809ca37ddf9c17694212001ea2b11111b9fe16906e2da982d985a2602eed46c2b4ede257c065a75d4c61f6609e72b9376f80231c080559f773860535c075a4c179384e913d2266772df80935b3e5ec9b146aefe70988d0bbe9e0c1b925981565ccc731c73f40f7020ed8d4075438fb8c7672c839aa7e91bb37b6b8ecd42d7bce78fa221911cb0984da090ee0f08f455f483ce7dce0717f291648eec076e449610696447f393a09c7a155456eefc7edec1904cdffabdb25559f44bafd12ddc442fa2aa027bc94860af6928567a6867bc6cfead4917473e08b4baeec83614b3d240a4178f744ff43fbf4583acb83e0ac5a584204dfc3c5c7aa91d2081b6b31b1e57ee692f44381d6e81b3ac34fe68ae8ade11a9d05c2c587756727dd4c80f626424d6d01bd0b6b73986616793fd2a35773f147705c362c0d98e6273cc2d8ad0c1d68805ef0ef77f7217dec5284a1785e029c06450f45ce0b21bc400edf824f4d3115ca24b6b8fc7f2b68d739ddb139a81ccf442a612c8ed5a6fa216147e25809f5b5da8217888c2d373b7649799d7b3ef96cc43e563d969432dfebc0cfcc48ac4b71960db69796594eefee48181905424aa274ca9170ca5d8498c789c";

    /**
     * This methode is used to extract the user email
     *
     * @param token the JWT token of the user
     * @return the email from the jwt token
     */
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * The generic methode extract saved @{@link Claims} / information in a JWT Token.
     * In this case it can be the username / email or the expiration date
     *
     * @param token takes the JWT token from expressly created for a user
     * @param claimsResolver holds a function of the class @{@link Claims}
     * @return re
     * @param <T> is generic placeholder ad takes a datatype of @{@link Claims}
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * The methode extract the date of expiration for the later validation.
     *
     * @param token takes the JWT token from expressly created for a user.
     * @return return the date of expiration for a JWT toke as @{@link Date}.
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * The method is overloaded and generate a new JWT toke for the user.
     *
     * @param userDetails takes a user as @{@link UserDetails}
     * @return a generated token that's encoded with a secret key and HS256 algorithm as @{@link String}
     */
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * The methode generate a new JWT toke for the user.
     * The Token is manipulated with the username and the claims as List.
     *
     * @param claims is a @{@link HashMap} for save extra claims.
     * @param userDetails takes a user as @{@link UserDetails} to save the neede Subjects
     * @return  a generated token that's encoded with a secret key and HS256 algorithm as @{@link String}
     */
    public String generateToken(Map<String, Object> claims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * The methode extract all claims from a JWT token.
     *
     * @param token takes the JWT token from expressly created for a user.
     * @return the @{@link Claims} from a token.
     */
    private Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // TODO: methods to add and extracted the role from / to a Token

    /**
     * The Methode returns the Key decoded as BASE64.
     *
     * @return te sign in key as @{@link Key}
     */
    private Key getSignInKey() {
        byte[] keyByte = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyByte);
    }

    /**
     * The methode checks if a token is expired.
     *
     * @param token the JWT token for a user
     * @return true if the token is 24 or more hours old. Fals is the token is younger than 24 hours.
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * The methode checks if a JWT token is for the user and if the token is
     *  expired.
     *
     * @param token takes the JWT token from a user
     * @param userDetails the user as @{@link UserDetails}
     * @return true if the token is valid or fals if the token isn't valid
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractEmail(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
