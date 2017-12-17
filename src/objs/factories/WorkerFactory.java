package objs.factories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import objs.Addiction;
import objs.Level;
import objs.Skill;
import objs.Worker;

public class WorkerFactory {

	public static Random random = new Random();
	
	/* Names generated from http://www.rinkworks.com/namegen/ */
	public static String namesCSV = "Smeif,Athph,Rist,Ingt,Morsh,Slas,Ustsh,Usami,Tael,Raigh,Theuq,Seird,"
			+ "Meynd,Mas,Tonz,Cernd,Othery,Ucere,Vord,Yundu,Nois,Oane,Gars,"
			+ "Oema,Usery,Nyl,Nin,Deeb,Uiai,Eoru,Adi,Tonk,Laiv,Chap,Odyno,"
			+ "Uardi,Vorst,Abana,Anrt,Yus,Duir,Wuib,Rayv,Ghak,Zhoos,Nysp,"
			+ "Treest,Ubano,Engy,Aity,Neeck,Word,Rayc,Avoru,Urany,Ytasy,Lut,"
			+ "Zayp,Ualeo,Droom,Edele,Aoso,Sulrt,Unta,Delph,Whoed,Theam,Risl,"
			+ "Oaso,Uryno,Emk,Rothc,Qued,Treiv,Achnn,Umph,Ingf,Nalg,Ieru,Eoni,"
			+ "Nyst,Osn,Orck,Polnd,Onyu,Aingo,Essr,Eena,Adch,Jauh,Ioldo,Ustd,"
			+ "Erise,Feyll,Yurna,Kan,Yadu,Uighti,Uveso,Eyera,Schib,Ildld,Nyn,Anysa,"
			+ "Brys,Gont,Zhiel,Kimr,Itt,Iyeru,Banm,Mouw,Hatn,Caeld,Eiau,Inysu,Queq,"
			+ "Uceru,Byrr,Ech,Toth,Chaq,Thoud,Troolt,Droq,Hond,Seug,Umoro,Ildl,Dard,"
			+ "Yaughi,Tieq,Mayg,Radk,Thoil,Clois,Atony,Ned,Iurnu,Iqua,Enrt,Bror,Oenu,"
			+ "Oryny,Itaie,Cheil,Otase,Inaly,Keph,Oildo,Ynyi,Eml,Stroon,Shuinn,Idara,"
			+ "Erothi,Untt,Iqueo,Danth,Snor,Sulld,Perck,Arsh,Chrauh,Myn,Anck,Orl,Aomi,"
			+ "Iesti,Boild,Yout,Oyeru,Untrd,Ygaro,Edano,Rhal,Teint,Coid,Arc,Snun,Iunty,"
			+ "Undld,Oari,Ekelu,Ceit,Yunda,Eyere,Snend,Tiad,Blid,Eelme,Omss,Uentha,Rish,"
			+ "Samd,Teend,Adf,Quez,Enh,Zheaph,Uase,Oraki,Serw,Adany,Straf,Undll,Bruinn,"
			+ "Tuigh,Asa,Akimo,Akeli,Treiy,Oveso,Okime,Git,Oormy,Naw,Tyk,Irotha,Oraku,"
			+ "Endnn,Yunto,Queth,Asame,Radnn,Eeldi,Eturu, Verm,Wary,Emnt,Tinsh,Ieny,"
			+ "Yisso,Ustr,Mour,Onw,Opero,Uacku,Geysh,Zeid,Tus,Erily,Uingy,Pern,Belb,"
			+ "Aelde,Lyen,Ial,Cail,Kinnd,Esst,Tonss,Danck,Akini,Skelnn,Yrisi,Oime,"
			+ "Yrynu,Mend,Ightrd,Umnt,Drat,Kimf,Elmt,Honrt,Turz,Untl,Oathe,Thrif,Iendo,"
			+ "Eildi,Eusta,Burss,Rilz,Hoerd,Loesh,Emore,Aghao,Eiri,Rair,Epoli,Taik,Nais,"
			+ "Ieme,Mosl,Koih,Wheiv,Morch,Iome,Orako,Sliesh,Verck,Echnd,Unts,Woel,Uskck,"
			+ "Launn,Verd,Treb,Zhoud,Awm,Yeche,Zist,Pest,Thig,Uormo,Aqueu,Atane,Issnt,"
			+ "Ashnd,Chaund,Teel,Urada,Vealt,Iechi,Phoont,Dral,Crith,Kinl,Torc,Miant,Das,"
			+ "Orr,Unde,Esule,Koil,Etheru,Asulo,Zus,Eormo,Oelma,Ybele,Asv,Byp,Rug,Iundi,"
			+ "Ousku,Onth,Loem,Brow,Banq,Ril,Bregh,Zhod,Shyg,Rim,Taiz,Zhoim,Atasi,Caust,"
			+ "Kalr,Loich,Click,Ebani,Eache,Keunn,Perd,Loel,Oighta,Bloip,Tans,Aende,Agari,"
			+ "Uvoru,Zhib,Teich,Tanl,Iuski,Risgh,Iworu,Tins,Aquea,Uradu,Sayll,Trys,Dynnd,"
			+ "Neur,Saick,Terr,Weylt,Samn,Quas,Uinae,Taes,Rans,Tiar,Ymosa,Ahato,Trarr,Anl,"
			+ "Etone,Rheunn,Alent,Hierr,Ingst,Polk,Anglt,Art,Juin,Skelr,Lyes,Bil,Kelrr,Kiml,"
			+ "Blend,Mayst,Hourd,Iara,Dan,Alel,Auski,Ewary,Essll,Slik,Ariso,Ras,Neaf,Toey,"
			+ "Swuid,Ights,Iesso,Enthnt,Rirt,Thraer,Lyeph,Easho,Shar,Honn,Deuld,Ildrt,Acht,"
			+ "Ousty,Stourd,Samb,Isk,Scheiy,Yvesa,Quirt,Quos,Enf,Strig,Oldnd,Foz,Oissu,Eary,"
			+ "Schik,Tait,Odele,Banf,Cernn,Ustv,Achl,Eesse,Hort,List,Essd,Ergh,Sneack,Yighto,"
			+ "Niat,Serr,Endph,Aumu,Irl,Burnn,Vaunt,Stow,Yeit,Whoul,Ormp,Kell,Ebely,Toud,Rakn,"
			+ "Okala,Sulst,Snenn,Zoz,Rynth,Ecere,Rakl,Achu,Jayt,Kaid,Noer,Oeri,Driack,Yeru,"
			+ "Iacki,Nof,Arayu,Stroop,Ess,Aloro,Reuf,Vis,Seut,Cour,Ciak,Iss,Quel,Snaes,Quor,"
			+ "Atld,Aormu,Haet,Enn,Quak,Ienu,Nah,Owary,Sull,Banlt,Tonl,Jooch,Enye,Bol,Kik,"
			+ "Iema,Yatu,Rakld,Oml,Unya,Dier,Ochei,Iperu,Iard,Swoev,Smek,Lel,Isn,Inei,Oety,"
			+ "Idyna,Estk,Aughth,Ries,Ums,Ebane,Easi,Sed,Tot,Feld,Strih,Nyss,Oisso,Oms,Eita,"
			+ "Rils,Urisy,Meid,Mig,Etld,Burrd,Engh,Houd,Trut,Yloro,Isck,Jef,Sliand,Yalee,Ines,"
			+ "Vuint,Yashu,Yageo,Okali,Nip,Peyw,Ighae,Oora,Uarde,Itrr,Ost,Aesse,Drauh,Ileru,"
			+ "Idrao,Uaugha,Udra,Yoro,Ineo,Nit,Uomi,Uskss,Saul,Kur,Onyo,Star,Vess,Nair,";
	
	public static ArrayList<String> names = new ArrayList<>(Arrays.asList(namesCSV.split(",")));

	
	
	public static Worker getPlainWorker() {
		String name = getName();
		return new Worker(name);
	}
	
	public static Worker getRandomWorker() {
		Random random = new Random();
		
		String name = getName();
		int moneyLevel = 1 + random.nextInt(2);
		int repLevel = 1 + random.nextInt(2);
		Addiction addictLevel = Addiction.values()[random.nextInt(Addiction.values().length)];
		
		HashMap<Skill, Level> skills = getSkills(ProjectCategory.values()[random.nextInt(ProjectCategory.values().length)]);

		
		Worker worker = new Worker(name, moneyLevel, repLevel, addictLevel, skills);

		
		return worker;
		
	}
	
	private static HashMap<Skill, Level> getSkills(ProjectCategory category) {
		List<Skill> categorySkills = category.getSkillsRequired();
		HashMap<Skill, Level> workerSkills = new HashMap<>();
		
		for (int i = 0; i < random.nextInt(categorySkills.size()); i++) {
			workerSkills.put(categorySkills.get(i), new Level(1 + random.nextInt(2)));
		}
		
		return workerSkills;
	}
	
	public static String getName() {
		Random random = new Random();
		String name = names.get(random.nextInt(names.size()));
		names.remove(name);
		
		return name;
	}
}
