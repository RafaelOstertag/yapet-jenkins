profiles = [
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
		"MAKE=make"
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
    "OpenBSd": [
	"default" : [
	    "env": [
		"CC=cc",
		"CXX=c++",
		"CPP=cpp",
		"LDFLAGS=",
		"CFLAGS=",
		"CXXFLAGS=",
		"XML_CATALOG_FILES=/usr/local/share/xml/catalog",
		"MAKE=gmake"
	    ],
	    "flags": [
		"--disable-silent-rules",
		"--enable-debug"
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
		"MAKE=gmake"
	    ],
	    "flags": [
		"--disable-silent-rules",
		"--enable-debug"
	    ]
	],
	"fb-clang50-ports" : [
	    "env": [
		"CC=clang50",
		"CXX=clang++50",
		"CPP=clang-cpp50",
		"LDFLAGS=-L/usr/local/llvm50/lib -Wl,-rpath -Wl,/usr/local/llvm50/lib",
		"CFLAGS=",
		"CXXFLAGS=",
		"MAKE=gmake"
	    ],
	    "flags": [
		"--disable-silent-rules",
		"--enable-debug"
	    ]
	],
	"fb-clang34-ports" : [
	    "env": [
		"CC=clang34",
		"CXX=clang++34",
		"CPP=clang-cpp34",
		"LDFLAGS=-L/usr/local/llvm34/lib -Wl,-rpath -Wl,/usr/local/llvm34/lib",
		"CFLAGS=",
		"CXXFLAGS=",
		"MAKE=gmake"
	    ],
	    "flags": [
		"--disable-silent-rules",
		"--enable-debug"
	    ]
	],
	"fb-clang35-ports" : [
	    "env": [
		"CC=clang35",
		"CXX=clang++35",
		"CPP=clang-cpp35",
		"LDFLAGS=-L/usr/local/llvm35/lib -Wl,-rpath -Wl,/usr/local/llvm35/lib",
		"CFLAGS=",
		"CXXFLAGS=",
		"MAKE=gmake"
	    ],
	    "flags": [
		"--disable-silent-rules",
		"--enable-debug"
	    ]
	],
	"fb-clang38-ports" : [
	    "env": [
		"CC=clang38",
		"CXX=clang++38",
		"CPP=clang-cpp38",
		"LDFLAGS=-L/usr/local/llvm38/lib -Wl,-rpath -Wl,/usr/local/llvm38/lib",
		"CFLAGS=",
		"CXXFLAGS=",
		"MAKE=gmake"
	    ],
	    "flags": [
		"--disable-silent-rules",
		"--enable-debug"
	    ]
	],
	"fb-gcc47-ports" : [
	    "env": [
		"CC=gcc47",
		"CXX=g++47",
		"CPP=cpp47",
		"LDFLAGS=-L/usr/local/lib/gcc47 -Wl,-rpath -Wl,/usr/local/lib/gcc47",
		"CFLAGS=",
		"CXXFLAGS=",
		"MAKE=gmake"
	    ],
	    "flags": [
		"--disable-silent-rules",
		"--enable-debug"
	    ]
	],
	"fb-gcc48-ports" : [
	    "env": [
		"CC=gcc48",
		"CXX=g++48",
		"CPP=cpp48",
		"LDFLAGS=-L/usr/local/lib/gcc48 -Wl,-rpath -Wl,/usr/local/lib/gcc48",
		"CFLAGS=",
		"CXXFLAGS=",
		"MAKE=gmake"
	    ],
	    "flags": [
		"--disable-silent-rules",
		"--enable-debug"
	    ]
	],
	"fb-gcc49-ports" : [
	    "env": [
		"CC=gcc49",
		"CXX=g++49",
		"CPP=cpp49",
		"LDFLAGS=-L/usr/local/lib/gcc49 -Wl,-rpath -Wl,/usr/local/lib/gcc49",
		"CFLAGS=",
		"CXXFLAGS=",
		"MAKE=gmake"
	    ],
	    "flags": [
		"--disable-silent-rules",
		"--enable-debug"
	    ]
	],
	"fb-gcc5-ports" : [
	    "env": [
		"CC=gcc5",
		"CXX=g++5",
		"CPP=cpp5",
		"LDFLAGS=-L/usr/local/lib/gcc5 -Wl,-rpath -Wl,/usr/local/lib/gcc5",
		"CFLAGS=",
		"CXXFLAGS=",
		"MAKE=gmake"
	    ],
	    "flags": [
		"--disable-silent-rules",
		"--enable-debug"
	    ]
	],
	"fb-gcc6-ports" : [
	    "env": [
		"CC=gcc6",
		"CXX=g++6",
		"CPP=cpp6",
		"LDFLAGS=-L/usr/local/lib/gcc6 -Wl,-rpath -Wl,/usr/local/lib/gcc6",
		"CFLAGS=",
		"CXXFLAGS=",
		"MAKE=gmake"
	    ],
	    "flags": [
		"--disable-silent-rules",
		"--enable-debug"
	    ]
	],
	"fb-gcc7-ports" : [
	    "env": [
		"CC=gcc7",
		"CXX=g++7",
		"CPP=cpp7",
		"LDFLAGS=-L/usr/local/lib/gcc7 -Wl,-rpath -Wl,/usr/local/lib/gcc7",
		"CFLAGS=",
		"CXXFLAGS=",
		"MAKE=gmake"
	    ],
	    "flags": [
		"--disable-silent-rules",
		"--enable-debug"
	    ]
	]
    ]
]
    
