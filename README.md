# Back-end-file-rouge

## Contexte

Pour valider ma formation de concepteur développeur d'applications, j'ai réalisé une application avec Spring Boot pour le back-end, MySQL pour la base de données et Nuxt, un framework basé sur Vue.js, pour le front-end. Le projet comprenait également la conception des diagrammes UML, des modèles MCD/MLD et des maquettes.

J'ai d'abord conçu les diagrammes de cas d'utilisation, d'activité et de classes afin de définir les fonctionnalités et les entités du projet. J'ai ensuite modélisé la base de données avec les diagrammes MCD et MLD, puis développé l'API avec Spring Boot et Docker. J'ai créé les entités, les modèles et les contrôleurs, ajouté Spring Security et Spring Data JPA, puis mis en place l'authentification par jetons JWT ainsi que des tests unitaires.

## Développement

Pour la réalisation du back-end, j’ai choisi d’utiliser le framework Spring Boot avec le langage Java, que j’ai découvert et pratiqué au cours de ma formation chez M2i Academy.

L’architecture retenue repose sur une API REST, qui permet de séparer clairement le front-end du back-end. Cette approche améliore la maintenabilité du projet, facilite les évolutions futures et renforce la sécurité en limitant l’accès direct aux données.

L’API REST permettra d’effectuer les opérations CRUD (Create, Read, Update, Delete) grâce aux différentes méthodes HTTP :

- GET : récupérer des données ;
- POST : créer de nouvelles ressources ;
- PUT/PATCH : modifier des ressources existantes ;
- DELETE : supprimer des ressources.

Afin de sécuriser l’accès à l’application, je prévois également de mettre en place un système d’authentification basé sur les JWT (JSON Web Tokens). Cette solution permettra aux utilisateurs de s’authentifier et d’accéder aux fonctionnalités en fonction de leurs droits.

### Dépendances utilisées

Plusieurs dépendances seront intégrées au projet afin de faciliter le développement et d’apporter des fonctionnalités complémentaires :

- Spring Boot Actuator : permet de surveiller et de gérer l’application en production grâce à des points d’accès (endpoints) fournissant des informations sur son état de fonctionnement.
- Spring Boot DevTools : améliore le confort de développement en proposant notamment le redémarrage automatique de l’application lors des modifications du code source.
- MySQL Driver : assure la connexion et les échanges entre l’application et la base de données MySQL.
- Lombok : réduit le code répétitif en générant automatiquement certaines méthodes courantes telles que les getters, setters, constructeurs ou encore la méthode toString() grâce à des annotations.
- Spring Web : fournit les composants nécessaires à la création de l’API REST et à la gestion des requêtes HTTP.
- Validation : permet de mettre en place des règles de validation sur les données. Par exemple, pour l’entité Book, le titre devra être obligatoire et limité à 50 caractères. Ainsi, une tentative d’ajout d’un livre sans titre sera automatiquement rejetée par le back-end.
- Spring Data JPA : simplifie les interactions avec la base de données relationnelle. En héritant de l’interface JpaRepository, un dépôt comme BookRepository bénéficie automatiquement des opérations CRUD de base sans nécessiter d’implémentation supplémentaire.
- Spring Security : renforce la sécurité de l’application et permet la mise en œuvre de l’authentification via les jetons JWT.

### Configuration de l’application

Après l’ajout des dépendances dans le fichier pom.xml, l’application sera configurée dans le fichier application.yml.

Cette configuration comprendra notamment :

- la définition d’un context-path pour personnaliser l’URL de l’application ;
- les paramètres de connexion à la base de données MySQL (URL, identifiant et mot de passe) ;
- la configuration de JPA afin de recréer automatiquement le schéma de la base de données lors du démarrage de l’application dans l’environnement de développement ;
- la génération et l’utilisation d’une paire de clés RSA (clé publique et clé privée) nécessaire à la création et à la validation des jetons JWT ;
- la définition du répertoire destinée au stockage et à l’envoi des images associées à l’application.

```bash
server:
  servlet:
    context-path: /m2l
spring:
  application:
    name: m2l
  datasource:
    url: jdbc:mysql://localhost:3306/m2l?createDatabaseIfNotExist=true
    username: root
    password:
  jpa:
    hibernate:
      ddl-auto: create
    show-sql: false
rsa:
  public-key: classpath:jwt/public.pem
  private-key: classpath:jwt/private.pem
images.path: 'C:/Users/mewen/OneDrive/Documents/back-end-fil-rouge/public/img/'
```

Pour finir la configuration, je crée les différents dossiers nécessaires pour le projet back-end comme celui de la sécurité, les contrôleurs, les entités...

### Vérification du fonctionnement

Une fois la configuration terminée, le projet sera lancé à l’aide de la commande suivante :

```.\mvnw.cmd spring-boot:run```

Cette étape permettra de vérifier que l’application démarre correctement et que l’ensemble des composants (API REST, base de données, sécurité et configuration) fonctionne comme prévu.

### Création des entités

Dans une application Spring REST API, une entité est une classe Java représentant une table de la base de données. Lors de la phase de conception, j'ai réalisé un diagramme de classes qui définit les différentes entités du système ainsi que leurs relations. Je m'appuie sur ce diagramme pour créer les entités dans le projet.

Pour illustrer cette étape, je prends l'exemple de l'entité **Article**.

#### Déclaration de l’entité

Au-dessus de la classe, j'ajoute plusieurs annotations :

- **@Entity** : indique à Spring et à JPA que cette classe correspond à une entité persistante en base de données.
- **@Data** : annotation Lombok qui génère automatiquement les getters, setters, ainsi que les méthodes ```equals()```, ```hashCode()``` et ```toString()```.
- **@Builder** : facilite la création d'objets grâce au patron de conception Builder.
- **@RequiredArgsConstructor** : génère un constructeur contenant uniquement les attributs obligatoires (annotés avec ```@NonNull```).
- **@AllArgsConstructor** : génère un constructeur contenant l'ensemble des attributs de la classe.
- **@NoArgsConstructor** : génère un constructeur sans paramètre, nécessaire notamment pour le fonctionnement de JPA.

#### Définition des attributs

L'entité **Article** contient plusieurs attributs. Voici quelques exemples :

L'attribut **id,** de type ```Integer```, représente la clé primaire de la table. Il est annoté avec :

- **@Id** : définit la clé primaire.
- **@GeneratedValue(strategy = GenerationType.IDENTITY)** : permet la génération automatique d'un identifiant unique par la base de données.

```bash
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
```

L'attribut **title**, de type ```String```, correspond au titre de l'article. Plusieurs validations lui sont appliquées :

- **@NonNull** : indique qu'il s'agit d'un champ obligatoire.
- **@NotEmpty** : vérifie que la chaîne de caractères n'est pas vide.
- **@Size(min = ..., max = ...)** : définit une longueur minimale et maximale autorisée.

```bash
    @NonNull
    @NotEmpty
    @Size(min = 1, max = 50)
    String title;
```

L'attribut **price**, de type ``Integer``, représente le prix de l'article. Afin de garantir l'intégrité des données, plusieurs contraintes sont ajoutées :

- **@NonNull** : rend le champ obligatoire.
- **@Min(1)** : impose une valeur minimale de 1.
- **@Max(1000)** : impose une valeur maximale de 1000.
- **@Positive** : vérifie que la valeur est strictement positive.

```bash
   @NonNull
    @Min(1)
    @Max(1000)
    @Positive
    Integer price;
```

L'attribut **addDate**, de type ```LocalDateTime```, enregistre la date et l'heure de création de l'article. Grâce à l'annotation **@Default**, une valeur par défaut est automatiquement attribuée lors de la création d'un nouvel article.

```bash
    @Default
    LocalDateTime addDate = LocalDateTime.now();
```

#### Mise place des relations entre entités

D'après le diagramme de classes et le Modèle Conceptuel de Données (MCD), l'entité **Article** possède une relation de type **One-to-Many** avec l'entité **CartItems** : un article peut être associé à plusieurs éléments de panier.

Dans l'entité **Article**, j'ajoute donc un attribut de type ```List<CartItems>``` qui est une liste de l’entité CartItems annoté avec @OneToMany afin de représenter cette relation.

Dans l'entité **CartItems**, j'ajoute un attribut article de type l’entité ```Article```, accompagné des annotations suivantes :

- **@NonNull** : l'association à un article est obligatoire.
- **@ManyToOne** : indique que plusieurs éléments de panier peuvent être associés à un même article.
- **@JoinColumn** : précise la colonne utilisée comme clé étrangère dans la base de données.

Une relation bidirectionnelle entre **Article** et **CartItems** peut provoquer un problème de récursion infinie lors de la conversion des objets en JSON. En effet, lorsqu'un article est récupéré, il contient ses ```CartItems``, qui contiennent eux-mêmes une référence vers l'article, et ainsi de suite.

Pour éviter ce problème, j'utilise l'annotation **@JsonIgnoreProperties** sur les deux entités. Cette annotation permet d'ignorer certaines propriétés lors de la sérialisation JSON et empêche ainsi les boucles infinies lors de l'envoi des données via l'API REST.

```bash
 @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("article")
    List<CartItem> cartItems = new ArrayList<>();
```

Le code de l’entité Article

```bash
    @NonNull
    @ManyToOne
    @JsonIgnoreProperties("cartItems")
    @JoinColumn(nullable = false)
    Article article;
```

Le code de l’entité CartItems

### Configuration des repository

Les repositories constituent la couche d’accès aux données de l’application et permettent au serveur de communiquer avec la base de données. Chaque repository est une interface associée à une entité métier et hérite de l’interface ```JpaRepository```, fournie par Spring Data JPA.

Cette interface met à disposition un ensemble de méthodes prédéfinies permettant d’effectuer les opérations CRUD (Create, Read, Update, Delete), qui sont ensuite utilisées par les services de l’application pour manipuler les données.

Il est également possible de définir des requêtes personnalisées de deux manières :

- **Par convention de nommage** : Spring Data JPA est capable de générer automatiquement une requête à partir du nom d’une méthode. Par exemple, une méthode nommée ci-dessous permettra de rechercher un utilisateur dont le nom d’utilisateur correspond à la valeur passée en paramètre.

```bash
User findByUsername(String username);
```

- **À l’aide de requêtes JPQL (Java Persistence Query Language)** : ce langage, proche du SQL, permet de créer des requêtes plus complexes. Dans l’exemple du repository ```Book```, la requête recherche les livres dont le titre correspond au paramètre fourni. Une fois les résultats obtenus, seuls l’identifiant et le titre des ouvrages sont sélectionnés puis retournés sous la forme d’un objet ```Page```. L’utilisation du paramètre ```Pageable``` permet de gérer efficacement la pagination des résultats.

```bash @Query("""
            SELECT DISTINCT id, CONCAT(title) FROM Book b WHERE
            LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
            """)
    Page<Book> selectBookByTitle(@Param("keyword") String keyword, Pageable pageable);
```

### Création des services

Les services constituent la couche intermédiaire entre l’application et la base de données. Leur rôle est de gérer les opérations métier liées aux entités, telles que la récupération, l’ajout, la modification ou la suppression de données.

Dans le cadre de ce projet, plusieurs services ont été mis en place :

- **ArticleService** : gestion des articles affichés sur la page d’accueil ;
- **AuthorService**, **BookService** et **EditorService** : gestion des auteurs, livres et éditeurs afin de pouvoir les associer aux articles ;
- **CartItemService** : gestion des éléments du panier ;
- **OrderService** : création et gestion des commandes lors de la validation du panier ;
- **InvoiceService** et **SaleService** : gestion des factures et des ventes ;
- **UserService** et **TokenService** : gestion des utilisateurs, de l’authentification et de la sécurité de l’application.

Chaque service est composé d’une **interface** définissant les méthodes à implémenter et d’une **classe de service** contenant leur implémentation. Cette organisation permet de respecter les principes de modularité et de maintenabilité du code.

#### Exemple n°1 : suppression d’un article

Pour supprimer un article, une méthode reçoit en paramètre un identifiant de type Int. Elle utilise ensuite la méthode ```findById()``` du repository associé à l’entité Article (fournie par ```JpaRepository```) afin de rechercher l’article correspondant.

Si l’article existe, la méthode ```delete()``` du repository est appelée pour le supprimer de la base de données, puis la méthode retourne ```true``` afin d’indiquer le succès de l’opération. Dans le cas contraire, elle retourne ```false```.

```bash
@Override
    public Boolean remove(int id) {
        Article article = articleRepository.findById(id).orElse(null);
        if (article != null) {
            articleRepository.delete(article);
            return true;
        }
        return false;
    }
```

#### Exemple n° 2 : création d’un article

Avant de mettre en place la méthode de création d’un article, un objet **DTO (Data Transfer Object)** a été créé. Son rôle est de faciliter le transfert des données nécessaires à la création d’un article entre les différentes couches de l’application.

```bash
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateArticle{
    Integer id;
    String title;
    Integer width;
    Integer height;
    Integer thickness;
    String number_isbn;
    Integer price;
    Integer stock;
    Integer editor;
    Integer book;
}
```

La méthode de création reçoit donc un DTO en paramètre. Elle commence par récupérer le livre et l’éditeur associés à partir de leurs identifiants respectifs. Si ces entités existent, un nouvel objet Article est construit à partir des données reçues puis enregistré dans la base de données grâce à la méthode ```save()``` du repository.

Si une erreur survient, notamment lorsque le livre ou l’éditeur n’est pas trouvé, la méthode retourne ```null``` afin de signaler l’échec de l’opération.

```bash
@Override
    public Article createArticle(@RequestBody CreateArticle request) {
        try {
            Book book = bookRepository.findById(request.getBook()).orElse(null);
            Editor editor = editorRepository.findById(request.getEditor()).orElse(null);
            if (book == null) {
                throw new RuntimeException("Aucun livre n'a été trouvé avec l'id du livre");
            }
            if (editor == null) {
                throw new RuntimeException("Aucun éditeur n'a été trouvé avec l'id de l'éditeur");
            }
            Article article = Article.builder()
                    .title(request.getTitle())
                    .width(request.getWidth())
                    .height(request.getHeight())
                    .thickness(request.getThickness())
                    .number_isbn(request.getNumber_isbn())
                    .price(request.getPrice())
                    .stock(request.getStock())
                    .editor(editor)
                    .book(book)
                    .build();
            return articleRepository.save(article);
        } catch (Exception e) {
            return null;
        }
    }
```

#### Exemple n°3 : recherche d’un éditeur

Le service de gestion des éditeurs contient également une méthode permettant de rechercher un éditeur à partir de son nom.

Cette méthode reçoit en paramètre une chaîne de caractères nommée keyword, provenant de l’URL de la requête. La valeur est d’abord décodée à l’aide de la classe ```URLDecoder``` de Java. Une recherche est ensuite effectuée afin de récupérer tous les éditeurs dont le nom correspond au mot-clé fourni, tout en respectant le système de pagination mis en place.

Enfin, la liste des résultats est retournée au client. Si une erreur survient lors du décodage du mot-clé, la méthode retourne ```null```.

```bash
 @Override
    public Page<Editor> findEditorByTitle(String keyword) {
        try {
            String urlSearch = URLDecoder.decode(keyword, StandardCharsets.UTF_8.name());
            Page<Editor> editors = editorRepository.selectEditorByTitle(urlSearch, PageRequest.of(0, 9));
            return editors;
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
```

### Mise en place des contrôleurs

Le contrôleur constitue l’interface entre le front-end et le serveur. Son rôle est de recevoir les requêtes envoyées par l’utilisateur depuis l’application web, puis de les transmettre au service correspondant qui se charge du traitement métier.

Pour communiquer avec l’API, chaque requête doit utiliser une URL valide ainsi qu’une méthode HTTP adaptée à l’action souhaitée :

- **GET** : permet de récupérer des données depuis le serveur.
- **POST** : permet d’ajouter de nouvelles données dans la base de données. Les informations à enregistrer sont transmises dans le corps (body) de la requête.
- **DELETE** : permet de supprimer des données existantes.
- **PUT** : permet de remplacer intégralement une ressource existante. Les nouvelles données sont transmises dans le corps de la requête.
- **PATCH** : permet de modifier partiellement une ressource sans remplacer l’ensemble de ses données.

Dans mon projet, chaque contrôleur est associé à une entité lorsque celle-ci nécessite une exposition via l’API. J’ai ainsi créé des contrôleurs pour les entités **Article**, **Author**, **Book**, **Editor**, **CartItem** et **Sale**.

Chaque contrôleur est annoté avec :

- **@RestController** : indique à Spring qu’il s’agit d’un contrôleur REST. Celui-ci ne gère pas l’affichage des pages mais uniquement l’échange de données au format JSON.
- **@RequestMapping** : permet de définir le chemin de base de l’URL associé au contrôleur.

J’utilise également l’annotation **@AllArgsConstructor** afin de générer automatiquement le constructeur contenant les dépendances nécessaires. Le service associé au contrôleur est ensuite injecté sous forme d’attribut afin de pouvoir accéder à la logique métier.

```bash
@RestController
@RequestMapping("/articles")
@AllArgsConstructor
public class ArticleController {
    private ArticleService articleService;
}
```

Après la création du contrôleur, je développe les différentes méthodes en m’appuyant sur le diagramme de classes et les besoins fonctionnels du projet. Chaque méthode est associée à une annotation correspondant à la méthode HTTP utilisée :

- **@GetMapping**
- **@PostMapping**
- **@PutMapping**
- **@DeleteMapping**
- **@PatchMapping**

Ces annotations prennent en paramètre le chemin complémentaire de l’URL permettant d’accéder à la méthode concernée.

Par exemple, si le contrôleur des articles possède le chemin de base ```/articles``` (définie avec l’annotation ```@RequestMapping```) et qu’une méthode est annotée avec ```@GetMapping("/all")```, l’URL complète permettant de récupérer tous les articles sera :

```https://monsite/articles/all```

Dans ce cas, la requête doit obligatoirement être envoyée avec la méthode HTTP **GET**. Si une autre méthode est utilisée, comme **POST** ou **DELETE**, la requête sera rejetée par l’API car elle ne correspond pas au mapping défini dans le contrôleur.

#### Exemple n°1 : Récupération d’une liste d’articles

Dans le contrôleur **Article**, j’ai mis en place une méthode ```findAllPageable``` permettant de récupérer les articles de manière paginée. Cette méthode reçoit deux paramètres : ```offset```, de type ```Integer```, qui indique la page à récupérer, et ```pageSize```, qui définit le nombre d’articles à retourner.

L’annotation ```@GetMapping``` est utilisée afin d’exposer cette méthode via une URL spécifique. Le chemin ```/{offset}/{pageSize}``` permet à Spring de récupérer les valeurs directement depuis l’URL. Les paramètres de la méthode sont donc annotés avec ```@PathVariable``` afin d’indiquer à Spring qu’ils doivent être extraits du chemin de la requête.

La méthode appelle ensuite le service dédié aux articles en lui transmettant les paramètres ```offset``` et ```pageSize```. Les données retournées par le service sont stockées dans une variable puis renvoyées dans la réponse HTTP.

```bash
    @GetMapping("/{offset}/{pageSize}")
    public ResponseEntity<Page<Article>> findAllPageable(@PathVariable int offset, @PathVariable int pageSize) {
        Page<Article> allArticles = articleService.findActiveArticles(offset, pageSize);
        return new ResponseEntity<Page<Article>>(allArticles, HttpStatus.OK);
    }
```

#### Exemple n°2 : Modification d’un article

J’ai également développé une méthode ```updateArticle``` permettant de modifier un article existant. Cette méthode reçoit deux paramètres : un objet ```article``` qui est le DTO ```CreateArticle```, annoté avec ```@RequestBody``` afin que Spring récupère les données présentes dans le corps de la requête, ainsi qu’un paramètre ```id``` de type ```Integer```, annoté avec ```@PathVariable```.

L’annotation ```@PutMapping("/{id}")``` permet d’associer l’identifiant présent dans l’URL au paramètre de la méthode. De plus, l’annotation ```@Secured("ROLE_ADMIN")``` restreint l’accès à cette fonctionnalité aux seuls utilisateurs possédant le rôle administrateur.

Dans un premier temps, la méthode vérifie que l’identifiant présent dans l’URL correspond à celui contenu dans l’objet reçu dans le corps de la requête. En cas d’incohérence, une réponse **400 Bad Request** est retournée. Si les identifiants correspondent, le service de gestion des articles est appelé afin d’effectuer la mise à jour. Si aucun article n’est trouvé, une réponse **404 Not Found** est renvoyée. Dans le cas contraire, l’article mis à jour est retourné avec un code de réponse **200 OK**.

```bash
@PutMapping("/{id}")
    @Secured({ "ROLE_ADMIN" })
    public ResponseEntity<Article> updateArticle(@RequestBody CreateArticle article, @PathVariable int id) {
        if (id != article.getId()) {
            return ResponseEntity.badRequest().build();
        }
        var b = articleService.update(article);
        if (b == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<Article>(b, HttpStatus.ACCEPTED);
    }
```

#### Exemple n°3 : Suppression d’un article

Enfin, une méthode de suppression d’article a été mise en place. Celle-ci reçoit un identifiant en paramètre et utilise l’annotation ```@DeleteMapping("/{id}")``` pour associer l’identifiant présent dans l’URL à celui de la méthode. L’accès à cette fonctionnalité est également protégé par l’annotation ```@Secured("ROLE_ADMIN")```, garantissant que seuls les administrateurs peuvent supprimer un article.

La méthode appelle ensuite le service de suppression des articles. Si l’article a été trouvé et supprimé avec succès, une réponse **204 No Content** est renvoyée. Dans le cas contraire, si aucun article correspondant à l’identifiant fourni n’existe, une réponse **404 Not Found** est retournée.

```bash
 @DeleteMapping("/{id}")
    @Secured({ "ROLE_ADMIN" })
    public ResponseEntity<Void> deleteArticle(@PathVariable int id) {
        var b = articleService.remove(id);
        if (b == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
```

### Sécurité

Après avoir configuré l’application, je me suis concentré sur la sécurisation de celle-ci. J’ai tout d’abord mis en place le mécanisme CORS (Cross-Origin Resource Sharing), qui permet de contrôler les échanges entre des applications hébergées sur des domaines différents. Pour cela, j’ai créé la classe **SecurityConfig** ainsi que la méthode **CorsConfigurationSource**. Cette configuration autorise uniquement le futur domaine du front-end à communiquer avec le serveur, limitant ainsi les risques liés aux requêtes provenant de sources non autorisées.

J’ai ensuite configuré la méthode **SecurityFilterChain**, qui permet de gérer les autorisations d’accès en fonction du rôle de l’utilisateur. Par exemple, les données de la table **Article** peuvent être consultées par tous les profils, qu’il s’agisse d’un visiteur, d’un utilisateur authentifié ou d’un administrateur. En revanche, seules les personnes disposant des droits appropriés peuvent créer, modifier ou supprimer des articles.

Je me suis ensuite consacré à la mise en place du système d’authentification et d’inscription des utilisateurs. Pour sécuriser les échanges entre le client et le serveur, j’ai implémenté l’utilisation de **JWT (JSON Web Tokens)**. Ces jetons permettent au serveur de vérifier l’identité de l’utilisateur et de s’assurer que les requêtes effectuées sont légitimes.

Lors de la connexion, deux jetons sont générés : un **Access Token** et un **Refresh Token**. L’Access Token permet à l’utilisateur d’accéder aux ressources protégées de l’application. Afin de renforcer la sécurité, sa durée de validité est volontairement limitée. Lorsque celui-ci expire, le Refresh Token, dont la durée de vie est plus longue, permet de générer un nouvel Access Token sans nécessiter une nouvelle authentification. En revanche, lorsque le Refresh Token arrive à expiration, l’utilisateur doit se reconnecter.

Un JWT est composé de trois parties distinctes :

- **Header** : contient les informations descriptives du jeton, notamment l’algorithme de signature utilisé.
- **Payload** : contient les données embarquées dans le jeton. Dans le cadre de mon projet, j’y stocke notamment l’adresse e-mail de l’utilisateur, son rôle, la date de création du jeton, sa date d’expiration ainsi que le nom du serveur émetteur.
- **Signature** : garantit l’intégrité et l’authenticité du jeton grâce à une signature numérique.

Enfin, j’ai développé deux méthodes essentielles à la gestion des JWT : **jwtEncoder** et **jwtDecoder**. La méthode **jwtEncoder** permet de générer les jetons à l’aide d’une paire de clés RSA publique et privée, tandis que **jwtDecoder** utilise la clé publique pour vérifier et décoder les jetons reçus par le serveur.

J'ai tout d'abord mis en place un repository dédié à l'entité **User**. Celui-ci contient notamment une méthode permettant de rechercher un utilisateur à partir de son adresse e-mail ainsi qu'une autre méthode permettant de le retrouver à partir de son identifiant unique.

```bash
    User findByEmail(String email);


    @Query(""" SELECT DISTINCT u FROM User u WHERE u.id = :keyword """)
    User selectUserById(@Param("keyword") Integer id);
```

J'ai ensuite développé un service nommé **TokenService**, dont le rôle est de gérer la création et le renouvellement des jetons JWT. Ce service possède comme dépendances un encodeur JWT, un décodeur JWT ainsi que le repository **User**.

La première méthode de ce service permet de générer un **Access Token** contenant un payload personnalisé pour chaque utilisateur. Ce payload inclut notamment la date de création du jeton, son auteur, sa date d'expiration, le rôle de l'utilisateur ainsi que son adresse e-mail. Une seconde méthode est chargée de générer un **Refresh Token**, dont la durée de validité est plus longue que celle de l'Access Token.

```bash
@Service
@AllArgsConstructor
public class TokenServiceImpl implements TokenService {
    @Autowired
    private JwtEncoder encoder;
    @Autowired
    private JwtDecoder decoder;
    @Autowired
    private UserRepository userRepository;


    @Override
    public String generateAccessTokenFromAuthentication(String email, String roles) {
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .issuedAt(Instant.now())
                .issuer("spring-ws-jwt")
                .expiresAt(Instant.now().plusSeconds(2 * 60))
                .claim("role", roles)
                .subject(email)
                .build();
        var jeton = encoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
        return jeton;
    }


    @Override
    public String generateRefreshToken(String email) {
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .issuedAt(Instant.now())
                .issuer("spring-ws-jwt")
                .expiresAt(LocalDateTime.now().plusYears(1).toInstant(ZoneOffset.ofHours(0)))
                .subject(email)
                .build();
        var jeton = encoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
        return jeton;
    }
```

Une troisième méthode permet de renouveler les jetons à partir d'un Refresh Token. Celui-ci est décodé afin de récupérer l'adresse e-mail de l'utilisateur. Le système recherche ensuite l'utilisateur correspondant dans la base de données. Si aucun utilisateur n'est trouvé, une erreur est retournée. Dans le cas contraire, un nouvel Access Token et un nouveau Refresh Token sont générés à l'aide des méthodes précédemment décrites.

```bash
 @Override
    public JwtResponseDto generateTokensFromRefreshToken(String refreshToken) {
        var decodeJwt = decoder.decode(refreshToken);
        var email = decodeJwt.getSubject();
        var user = userRepository.findByEmail(email);
        if (user == null) {
            throw new BadCredentialsException("Utilisateur inexistant");
        }
        var role = user.getRole().name();
        return new JwtResponseDto(
                generateAccessTokenFromAuthentication(email, role),
                generateRefreshToken(email));
    }
```

#### Authentification

Pour gérer l'authentification, j'ai créé un DTO nommé **UserRequestDto** contenant les informations nécessaires à la connexion : l'adresse e-mail, le mot de passe, le Refresh Token ainsi que le type d'authentification souhaité (connexion par mot de passe ou par Refresh Token).

La méthode **Authenticate**, exposée via l'annotation ```@PostMapping```, reçoit un objet ```UserRequestDto``` en paramètre. Elle commence par vérifier le mode d'authentification demandé.

Lorsque l'utilisateur se connecte à l'aide de son mot de passe, la méthode appelle la fonction **checkUser** du service utilisateur. Cette fonction vérifie l'existence du compte associé à l'adresse e-mail fournie et compare le mot de passe saisi avec celui enregistré dans la base de données. Comme les mots de passe sont stockés sous forme hachée, une comparaison sécurisée est effectuée. Si les identifiants sont invalides, une erreur est retournée avec le message « Identifiant inexistant ». Dans le cas contraire, un Access Token et un Refresh Token sont générés puis renvoyés au client.

Si l'utilisateur choisit de se connecter à l'aide d'un Refresh Token, celui-ci est validé puis utilisé pour générer une nouvelle paire de jetons, qui est ensuite retournée au client.

```bash
 @PostMapping("/authenticate")
    public JwtResponseDto authenticate(@RequestBody UserRequestDto userDto) {
        if (userDto.getGrantType().name().equalsIgnoreCase("password")) {
            User user = userService.checkUser(userDto.getEmail(), userDto.getPassword());
            var accessToken = tokenService.generateAccessTokenFromAuthentication(user.getEmail(),
                    user.getRole().name());
            var refreshToken = tokenService.generateRefreshToken(user.getEmail());
            return new JwtResponseDto(accessToken, refreshToken);
        } else if (userDto.getGrantType().name().equalsIgnoreCase("refresh_token")) {
            var tokens = tokenService.generateTokensFromRefreshToken(userDto.getRefreshToken());
            return tokens;
        }
        return null;
    }
```

La méthode authenticate du contrôleur de JWT

```bash
@Override
    public User checkUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new BadCredentialsException("Identifiant invalides");
        }
        if (!encoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Identifiants invalides");
        }
        return user;
    }
```

La méthode checkUser du service de User

#### Création d'un compte utilisateur

Une seconde méthode permet de créer un nouveau compte utilisateur. Celle-ci est annotée avec ```@PostMapping``` afin de traiter les requêtes HTTP POST et avec ```@ResponseStatus(HttpStatus.CREATED)``` afin de retourner automatiquement le code de statut **201 (Created)** lors de la création réussie d'un compte.

Lors de l'inscription, le rôle **USER** est automatiquement attribué au nouvel utilisateur afin de distinguer les comptes standards des comptes administrateurs. Le mot de passe est ensuite haché avant d'être enregistré dans la base de données.

Le hachage constitue une mesure de sécurité essentielle. Contrairement au chiffrement, il est irréversible : il n'est pas possible de retrouver le mot de passe original à partir de sa valeur hachée. De plus, un même mot de passe peut produire des résultats, ce qui renforce la sécurité du stockage des identifiants.

Une fois ces traitements effectués, le service utilisateur est utilisé pour enregistrer le nouvel utilisateur dans la base de données.

```bash
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@RequestBody User user) {
        user.setRole(Role.USER);
        user.setPassword(encoder.encode(user.getPassword()));
        return userService.save(user);
    }
```

La méthode register() du contrôleur JWT

```bash
    @Override
    public User save(@Valid User user) {
        return userRepository.save(user);
    }
```

La méthode save du service de l’utilisateur

#### Récupération des informations d'un utilisateur

Enfin, une troisième méthode permet de récupérer les informations d'un utilisateur à partir de son adresse e-mail.

La recherche est effectuée via le service utilisateur. Les données sont ensuite retournées sous la forme d'un **UserDataDto**, qui ne contient pas le mot de passe afin de garantir la confidentialité des informations sensibles.

Si aucun utilisateur ne correspond à l'adresse e-mail fournie, la méthode retourne une erreur **404 (Not Found)**. Dans le cas contraire, les informations de l'utilisateur sont envoyées avec un code de réponse **200 (OK)**.

```bash
 @GetMapping("/getUser/{email}")
    public ResponseEntity<UserDataDto> getUser(@PathVariable String email){
        var user = userService.getUser(email);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<UserDataDto>(user, HttpStatus.OK);
    }
```

La méthode getUser du contrôleur de JWT

```bash
    @Override
    public UserDataDto getUser(String email) {
        User user = userRepository.findByEmail(email);
        return new UserDataDto(user.getId(), user.getLastname(), user.getFirstname(), user.getPhone_number(),
                user.getEmail(), user.getBilling_address(), user.getDelivery_address());
    }
```

La méthode getUser du service de User

### Mise en place des tests unitaires

Après avoir finalisé le développement du back-end, j’ai entrepris la mise en place des tests afin de vérifier le bon fonctionnement de l’application et d’anticiper au maximum les éventuels dysfonctionnements.

Il existe plusieurs catégories de tests :

- **Les tests unitaires** : ils permettent de vérifier le comportement d’une partie spécifique de l’application (contrôleur, service, composant, etc.) de manière isolée. Leur objectif est de s’assurer que chaque élément retourne le résultat attendu dans différentes situations.

- **Les tests d’intégration** : ils permettent de tester le fonctionnement global de plusieurs composants ensemble. Dans le cadre d’une application Spring Boot, ils peuvent être réalisés à l’aide d’une base de données H2, une base en mémoire qui reproduit le comportement d’une base de données réelle tout en facilitant l’exécution des tests.

En raison des contraintes de temps du projet, j’ai choisi de me concentrer sur les tests unitaires des contrôleurs, qui constituent un élément essentiel de l’architecture de l’application.

Avant de pouvoir exécuter ces tests, il a été nécessaire de modifier le fichier **application.yml** situé dans le répertoire des tests. Cette configuration permet notamment de désactiver certaines auto-configurations des contrôleurs qui provoquaient systématiquement des erreurs lors du démarrage du contexte de test.

```bash
spring:
  autoconfigure:
    exclude: org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration, org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration, org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration
```

J’ai ensuite créé une classe de test nommée **AuthorControllerUnitTest** dans le dossier dédié aux tests. Cette classe est annotée avec :

- **@SpringBootTest**, afin de charger le contexte Spring nécessaire à l’exécution des tests ;
- **@AutoConfigureMockMvc**, afin de configurer automatiquement l’outil MockMvc, qui permet de simuler des requêtes HTTP sans avoir à démarrer un serveur web.

Enfin, j’ai injecté une instance de **MockMvc** ainsi que les différents services utilisés par le contrôleur. Ces services sont remplacés par des objets simulés (mocks) grâce à l’annotation **@MockitoBean**, ce qui permet de tester uniquement le comportement du contrôleur sans dépendre de l’implémentation réelle des services.

```bash
@SpringBootTest
@AutoConfigureMockMvc
public class AuthorControllerUnitTest {
        @Autowired
        private MockMvc mockMvc;
        @MockitoBean
        private AuthorService authorService;
        @MockitoBean
        private BookServiceImpl bookService;
        @MockitoBean
        private ArticleService articleService;
        @MockitoBean
        private CartItemService cartItemService;
        @MockitoBean
        private CommandeService commandeService;
        @MockitoBean
        private EditorService editorService;
        @MockitoBean
        private FactureService factureService;
        @MockitoBean
        private SaleService saleService;
        @MockitoBean
        private UserService UserService;
        @MockitoBean
        private TokenService tokenService;
        @MockitoBean
        private UserDetailsService userDetailsService;
}
```

Je vais maintenant aborder la phase de tests. Pour chaque méthode de test ajoutée au projet, j’utilise l’annotation ```@Test``` afin d’indiquer qu’il s’agit d’une méthode destinée à la validation du comportement de l’application. Voici quelques exemples de test réalisés :

#### Exemple N°1 :Tests de récupération d’une liste d’auteur

Dans un premier temps, j’ai créé plusieurs méthodes de test pour le contrôleur ```Author```. L’objectif est de vérifier que la récupération de la liste des auteurs fonctionne correctement et qu’aucune erreur n’est générée.

Pour cela, je crée une méthode annotée avec ```@Test``` et ```@WithMockUser```, cette dernière permettant de simuler un utilisateur possédant le rôle **Admin**. À l’intérieur de la méthode, je crée une liste d’auteurs fictifs puis je configure le mock du service afin que l’appel à la méthode ```findAll()``` retourne cette liste.

J’utilise ensuite l’objet ```MockMvc``` pour exécuter une requête HTTP de type **GET** vers l’endpoint du contrôleur concerné. Je vérifie alors que :

- Le code de retour est **200 (OK)** ;
- La réponse n’est pas vide ;
- Le premier auteur retourné possède le même nom de famille que celui défini dans la liste de test.

Enfin, je vérifie que la méthode ```findAll()``` du service a bien été appelée.

```bash
@Test
@WithMockUser(username = "doe", roles = { "ADMIN" })
void testGetAuthorAdmin() throws Exception {
        List<Author> fakedAuthors = List.of(
                        new Author().builder()
                                        .lastname("Dae")
                                        .firstname("Jane")
                                        .langue(Langage.DE)
                                        .build(),
                        new Author().builder()
                                        .lastname("Michel")
                                        .firstname("Pata")
                                        .langue(Langage.ES)
                                        .build(),
                        new Author().builder()
                                        .lastname("Doe")
                                        .firstname("John")
                                        .langue(Langage.GB)
                                        .build(),
                        new Author().builder()
                                        .lastname("Dupont")
                                        .firstname("Martin")
                                        .langue(Langage.FR)
                                        .build());
        Page<Author> fakedPageAuthor = new PageImpl<>(fakedAuthors);
        when(authorService.findAll()).thenReturn(fakedPageAuthor);


        mockMvc
                        .perform(get("/author/all"))
                        .andExpect(status().is(200))
                        .andExpect(jsonPath("$").isNotEmpty())
                        .andExpect(jsonPath("$.content[0].lastname").value("Dae"));
        verify(authorService).findAll();
}
```

J’ai ensuite créé deux variantes de ce test :

- une première avec un utilisateur ayant le rôle **User** ;
- une seconde sans annotation ```@WithMockUser```, simulant ainsi un utilisateur non authentifié.

Dans ces deux cas, les résultats attendus sont différents : une erreur **500** pour l’utilisateur ne disposant pas des droits nécessaires et une erreur **401 (Unauthorized)** lorsqu’aucun utilisateur n’est authentifié.

```bash
mockMvc
       .perform(get("/author/all"))
       .andExpect(status().is(500));
```

Le mock de la méthode où l’utilisateur à le rôle "User"

```bash
mockMvc
       .perform(get("/author/all"))
       .andExpect(status().is(401));
```

Le mock de la méthode où il n’y pas d’utilisateur

#### Test de récupération d’un auteur par son identifiant

Dans un second ensemble de tests, je vérifie le bon fonctionnement de la méthode permettant de récupérer un auteur à partir de son identifiant.

Je crée une méthode de test avec les annotations ```@Test``` et ```@WithMockUser(roles = "Admin")```. Je construis ensuite un objet auteur à l’aide du DTO ```CreateAuthor```, puis je configure le mock du service pour que la méthode ```findById()``` retourne cet auteur lorsque l’identifiant demandé correspond à celui défini dans le test.

À l’aide de ```MockMvc```, j’exécute une requête **GET** sur l’endpoint concerné et je vérifie que :

- le statut retourné est **200 (OK)** ;
- le corps de la réponse n’est pas vide ;
- l’identifiant de l’auteur retourné est bien égal à **1**.

Je vérifie également que la méthode ```findById()``` du service a été correctement appelée.

J’ai créé une seconde méthode similaire avec un utilisateur possédant le rôle User. Dans ce cas, le comportement attendu est identique à celui obtenu avec un administrateur.

```bash
@Test
@WithMockUser(username = "doe", roles = { "ADMIN" })
void testGetFindByIdAuthorAdmin() throws Exception {
        CreateAuthor createAuthor = new CreateAuthor(1, "Doe", "John", Langage.DE);
        when(authorService.findById(1)).thenReturn(createAuthor);
        mockMvc
                        .perform(get("/author/1"))
                        .andExpect(status().is(200))
                        .andExpect(jsonPath("$").isNotEmpty())
                        .andExpect(jsonPath("$.id").value(1));
        verify(authorService).findById(1);
}
```

Afin de tester la gestion des erreurs, j’ai également développé un scénario dans lequel la requête tente de récupérer un auteur inexistant. Je vérifie alors que :

- Le statut retourné est 404 (Not Found) ;
- Aucune donnée n’est présente dans la réponse ;
- La méthode findById() a bien été appelée avec l’identifiant incorrect.

```bash
mockMvc
                .perform(get("/author/2"))
                .andExpect(status().is(404))
                .andExpect(jsonPath("$").doesNotHaveJsonPath());
verify(authorService).findById(2);
```

#### Test de suppression d’un auteur

Le troisième ensemble de tests concerne la suppression d’un auteur.

J’ai créé une méthode nommée ```testDeleteAuthorAdmin```, annotée avec ```@Test``` et ```@WithMockUser```. Une variable id contenant la valeur 1 est définie, puis le mock du service est configuré afin que l’appel à la méthode ```remove(id)``` retourne la valeur booléenne **true**.

À l’aide de ```MockMvc```, j’exécute une requête HTTP de type **DELETE** visant à supprimer l’auteur dont l’identifiant est **1**. Je vérifie ensuite que :

- le statut retourné est **204 (No Content)** ;
- la méthode ```remove()``` du service a bien été appelée.

```bash
@Test
@WithMockUser(username = "doe", roles = { "ADMIN" })
void testDeleteAuthorAdmin() throws Exception {
        int id = 1;
        when(authorService.remove(id)).thenReturn(true);
        mockMvc
                        .perform(delete("/author/1"))
                        .andExpect(status().isNoContent());
        verify(authorService).remove(id);
}

```
Comme pour les tests précédents, plusieurs variantes ont été mises en place :

- un test avec un identifiant inexistant, qui doit retourner une erreur **404 (Not Found)** ;
- un test avec un utilisateur ne disposant pas des droits administrateur, qui doit retourner une erreur **500** ;
- un test sans utilisateur authentifié, qui doit retourner une erreur **401 (Unauthorized)**.

#### Exécution des tests

Après chaque ajout ou modification de test, j’exécute la commande suivante :

```bash
.\mvnw.cmd test
```

Cette commande lance automatiquement l’ensemble des tests du projet et affiche le nombre de tests exécutés ainsi que leur résultat. Pour valider le bon fonctionnement de l’application, aucun test ne doit échouer ni générer d’erreur.

### Gestion des images :

Pour la gestion des livres, il est nécessaire de stocker une image représentant leur couverture. Pour répondre à ce besoin, j’ai commencé par créer une méthode permettant d’ajouter un livre accompagné de son image au sein du contrôleur ```BookController```.

J’ai ainsi développé la méthode ```addBook()```, annotée avec ```@PostMapping``` sans paramètre. Cela signifie que la méthode est accessible via une requête HTTP POST sur l’URL du contrôleur (par exemple: ```https://monsite.fr/book```). J’ai également ajouté l’annotation ```@Secured``` afin de restreindre l’accès à cette fonctionnalité aux seuls administrateurs. Enfin, l’annotation ```@ResponseStatus(HttpStatus.CREATED)``` permet d’indiquer qu’en cas de succès, la méthode doit retourner le code HTTP **201 (Created)**.

Lors de la réception de la requête, je m’attends à ce que les données soient envoyées sous forme multipart : d’un côté les informations du livre, et de l’autre le fichier image. C’est pourquoi la méthode reçoit deux paramètres : ```book```, qui contient les informations du livre, et ```imageFile```, de type ```MultipartFile```, permettant de manipuler le fichier image transmis. Les deux paramètres sont annoté de @RequestPart pour que Spring comprenne que les données sont sous forme multipart.

La méthode du contrôleur délègue ensuite le traitement au service métier chargé de l’ajout des livres. Dans ce service, je commence par récupérer l’extension du fichier image (PNG, JPG, WEBP, etc.). Je crée ensuite une variable de type Path représentant l’emplacement où l’image sera enregistrée. Afin d’éviter tout conflit de noms, le nom original du fichier est remplacé par une chaîne de caractères générée aléatoirement, tout en vérifiant qu’aucune image existante ne possède déjà ce nom. L’extension précédemment récupérée est ensuite ajoutée au nom généré.

Par la suite, je récupère les auteurs associés au livre et je vérifie que cette liste n’est pas vide. Une fois cette validation effectuée, je crée l’entité représentant le livre, j’y renseigne l’ensemble des données nécessaires, notamment le nom de l’image enregistrée, puis je sauvegarde l’entité dans la base de données.

Si l’opération se déroule correctement, le service retourne les informations du livre créé avec le code HTTP **201 (Created)**. En revanche, si une erreur survient au cours du processus, une réponse d’erreur **400 (Bad Request)** est renvoyée au client.

```bash
@PostMapping
    @Secured({ "ROLE_ADMIN" })
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addBook(@RequestPart CreateBook book, @RequestPart MultipartFile imageFile) {
        try {
            Book createBook = bookService.createBook(book, imageFile);
            return new ResponseEntity<>(createBook, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
```

Le contrôleur pour ajouter un livre ainsi que son image

```bash
    @Override
    public Book createBook(@RequestBody CreateBook request, MultipartFile imageFile) throws IOException {
        String extension = imageFile.getOriginalFilename().substring(imageFile.getOriginalFilename().lastIndexOf("."));
        Path p;
        do {
            p = Paths.get(env.getProperty("images.path") + UUID.randomUUID() + extension);
        } while (Files.exists(p));
        Files.copy(imageFile.getInputStream(), p);
        List<Author> authors = authorRepository.findAllById(request.getAuthors());
        if (authors.isEmpty()) {
            throw new RuntimeException("No authors found with provided IDs");
        }
        Book book = Book.builder()
                .title(request.getTitle())
                .synopsis(request.getSynopsis())
                .style(request.getStyle())
                .image(p.getFileName().toString())
                .date(request.getDate())
                .authors(authors)
                .build();
        Book savedBook = bookRepository.save(book);
        return savedBook;
    }
```

Le service pour ajouter un livre ainsi que son image

Pour permettre l’affichage des images, j’ai implémenté une méthode dans la classe ```SecurityConfig```. Cette méthode analyse l’URL de la requête afin de vérifier que son premier segment correspond à « files ». Si c’est le cas, elle récupère le nom de l’image contenu dans le second segment de l’URL (par exemple : ```https://monsite.fr/files/image.webp```), charge le fichier correspondant et le renvoie dans la réponse HTTP.

```bash
@Bean
    RouterFunction<ServerResponse> staticResourceLocator(Environment env) {
        return RouterFunctions.resources("/files/**", new FileSystemResource(env.getProperty("images.path")));
    }
```

 Le fonction pour renvoyer une image

J’ai ensuite développé une méthode permettant de retourner une liste de livres. Chaque livre contient le nom de son image associée. Le client doit alors effectuer une requête supplémentaire vers l’URL de l’image afin de la récupérer et de l’afficher.

Par la suite, j’ai créé une nouvelle méthode permettant de modifier un livre tout en offrant la possibilité de remplacer son image. Pour cela, les données du livre et celles de l’image sont transmises séparément dans les paramètres de la requête. Ces informations sont ensuite envoyées à la méthode update du BookService. Cette dernière récupère le nom de l’ancienne image, la supprime du système de fichiers, puis l’enregistre à nouveau avec la nouvelle image fournie.

```bash
    @Secured({ "ROLE_ADMIN" })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> updateBook(@RequestPart CreateBook book, @RequestPart MultipartFile imageFile,
            @PathVariable int id) {
        if (id != book.getId()) {
            return ResponseEntity.badRequest().build();
        }
        try {
            var b = bookService.update(book, imageFile);
            if (b == null) {
                return ResponseEntity.notFound().build();
            }
            return new ResponseEntity<Book>(b, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
```

Le contrôleur pour modifier un livre

```bash
    @Override
    public Book update(@RequestBody @Valid CreateBook request, @RequestBody MultipartFile imageFile) {
        try {
            String oldImageBook = bookRepository.selectImageBookById(request.getId());
            Files.delete(Paths.get(env.getProperty("images.path") + oldImageBook));
            String extension = imageFile.getOriginalFilename()
                    .substring(imageFile.getOriginalFilename().lastIndexOf("."));
            String newImageName = oldImageBook.substring(0, oldImageBook.lastIndexOf(".")) + extension;
            Files.copy(imageFile.getInputStream(), Paths.get(env.getProperty("images.path") + newImageName));
            List<Author> authors = authorRepository.findAllById(request.getAuthors());
            if (authors.isEmpty()) {
                throw new RuntimeException("No authors found with provided IDs");
            }
            Book book = bookRepository.findById(request.getId())
                    .orElseThrow(() -> new RuntimeException("Livre non trouvé"));
            book.setTitle(request.getTitle());
            book.setSynopsis(request.getSynopsis());
            book.setStyle(request.getStyle());
            book.setImage(newImageName);
            book.setDate(request.getDate());
            book.setAuthors(authors);
            return bookRepository.save(book);
        } catch (Exception e) {
            return null;
        }
    }
```

Le service pour modifier une image

Enfin, j’ai implémenté la méthode de suppression d’un livre. Celle-ci fait appel au service métier qui vérifie d’abord si une image est associée au livre et la supprime le cas échéant. Le livre est ensuite supprimé de la base de données. Si l’opération se déroule correctement, le service retourne la valeur booléenne true, permettant au contrôleur de répondre avec le code HTTP ```204 No Content```. En revanche, si le livre n’est pas trouvé, le service retourne **false** et le contrôleur renvoie alors le code HTTP ```404 Not Found```. 

```bash
    @DeleteMapping("/{id}")
    @Secured({ "ROLE_ADMIN" })
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        var b = bookService.remove(id);
        if (b == false) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
```

Le contrôleur pour supprimer un livre

```bash
    @Override
    public Boolean remove(int id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            try {
                book.getAuthors().clear();
                Path p = Paths.get(env.getProperty("images.path") + book.getImage());
                bookRepository.delete(book);
                Files.deleteIfExists(p);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
```

Le service pour supprimer un livre