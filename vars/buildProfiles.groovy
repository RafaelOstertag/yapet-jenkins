// Build profiles used on various operating systems.

/**
 * The general format is:
 *
 * // ...
 * "<OS>": [
 *    "<PROFILE>" : [
 *       "env" : [ ]
 *       "flags" : []
 *    ]
 * ]
 *
 * Where `<OS>` is the operating system name which is passed to
 * `yapet.build()`. `<PROFILE>` is the profile name. 
 *
 * `"env"` contains environment variables passed to the configure
 * shell script and make. It must contain 'MAKE=', since it's
 * existence is assumed by the stages called in `yapet.build()`.
 *
 * `"flags"` are passed as command line arguments to the configure
 * shell script.
 */

@groovy.transform.Field
profiles = [
    //
    // Solaris
    //
    "Solaris": [
	"default" : [
	    "env": [
		"CC=cc",
		"CXX=CC",
		"LDFLAGS=",
		"CFLAGS=",
		"CXXFLAGS=",
		"MAKE=gmake"
		// ,
		// "XML_CATALOG_FILES=/etc/xml/catalog"
	    ],
	    "flags": [
		"--disable-silent-rules",
		"--enable-debug"
	    ]
	]
    ],
    //
    // Linux
    //
    "Linux": [
	"default" : [
	    "env": [
		"CC=cc",
		"CXX=c++",
		"CPP=cpp",
		"LDFLAGS=",
		"CFLAGS=",
		"CXXFLAGS=",
		"MAKE=make",
		"XML_CATALOG_FILES=/etc/xml/catalog"
	    ],
	    "flags": [
		"--disable-silent-rules",
		"--enable-debug"
	    ]
	]
    ],
    //
    // OpenBSD
    //
    "OpenBSD": [
	"default" : [
	    "env": [
		"CC=cc",
		"CXX=c++",
		"CPP=cpp",
		"LDFLAGS=",
		"CFLAGS=",
		"CXXFLAGS=",
		"XML_CATALOG_FILES=/usr/local/share/xml/docbook/catalog.xml",
		"MAKE=gmake",
		"AUTOCONF_VERSION=2.69",
		"AUTOMAKE_VERSION=1.15"
	    ],
	    "flags": [
		"--disable-silent-rules",
		"--enable-debug"
	    ]
	]
    ],
    //
    // NetBSD
    //
    "NetBSD": [
	"default" : [
	    "env": [
		"CC=cc",
		"CXX=c++",
		"CPP=cpp",
		"LDFLAGS=",
		"CFLAGS=",
		"CXXFLAGS=",
		"XML_CATALOG_FILES=/usr/pkg/share/xml/catalog",
		"MAKE=gmake"
	    ],
	    "flags": [
		"--disable-silent-rules",
		"--enable-debug",
		"--with-openssl=/usr"
	    ]
	]
    ],
    //
    // FreeBSD
    //
    "FreeBSD": [
	"default" : [
	    "env": [
		"CC=cc",
		"CXX=c++",
		"CPP=cpp",
		"LDFLAGS=",
		"CFLAGS=",
		"CXXFLAGS=",
		"MAKE=gmake",
		"XML_CATALOG_FILES=/usr/local/share/xml/catalog"
	    ],
	    "flags": [
		"--disable-silent-rules",
		"--enable-debug"
	    ]
	]
    ]
]
